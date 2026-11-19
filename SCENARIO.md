# Scenario — Meridian Mutual

> Meridian Mutual and every person, vendor, system name and figure in this assessment are fictional.

## The company

Meridian Mutual is a digital insurer operating in **Mexico, Colombia and Chile**. It sells auto, home and personal accident insurance directly to customers through its digital channel, and through advisors who use the same digital tools to assist customers.

| Country | Code | Active policies | Currency |
|---|---|---|---|
| Mexico | MX | ≈ 410,000 | MXN |
| Colombia | CO | ≈ 520,000 | COP |
| Chile | CL | ≈ 280,000 | CLP |

## Your role

You join as **Technical Lead of the Direct Digital Channel cell**. The cell implements, evolves and supports the quote-and-buy and self-service journeys of the three countries. You are the technical reference of the cell: you define its architecture and standards, make and document its key decisions, keep its services available and performant, guide internal developers and the developers of a third-party vendor, and translate business needs into technical implications together with the Technical Leads of other cells.

You report to the Director of Digital Channels. You do not manage people.

## The ecosystem

| Building block | What it is at Meridian |
|---|---|
| Cloud | **Azure** is the main cloud. Frontends and channel back ends (BFFs) run on Azure App Service or Azure Container Apps. Channel data lives in Azure Database for PostgreSQL. Monitoring uses Azure Monitor and Application Insights. Secrets live in Azure Key Vault. Security has approved two Azure regions for production: a primary region and a disaster-recovery region. |
| Integration platform | **MuleSoft Anypoint** with its **API Manager**. Integration follows an API-led approach with three layers: **Experience** APIs (adapted to a channel), **Process** APIs (business orchestration) and **System** APIs (one per back-end system). Every integration between platforms goes through it. |
| Identity | One **OIDC** identity provider with two realms: **customers** (self-registration) and **workforce** (advisors and staff, MFA mandatory). Access tokens are JWTs. |
| CRM and policy core | **Salesforce**, with **one org per country**. In each org, the same Salesforce instance holds the CRM (accounts, contacts, cases) and the policy core (products, rating, quotes, policies) on an insurance data model. See [`context/SALESFORCE_CONSTRAINTS.md`](context/SALESFORCE_CONSTRAINTS.md). |
| Event broker | A managed message broker on Azure, used for asynchronous integration between cells. |
| Communications | A regional SaaS platform for email, SMS and WhatsApp, with one business unit per country, owned by the Communications cell. |

## The current situation

The quote-and-buy journey was built three times:

- **Colombia** was built first by the internal team, using the integration platform as intended.
- **Chile** copied the Colombian code in 2024 and has diverged since.
- **Mexico** was built in 2023 by **Nimbus Digital**, a third-party vendor that still maintains it. It has its own frontend fork and its own BFF, and part of it calls Salesforce directly.

In 2026 the cell introduced a regional **Quote Experience API** (`quote-xapi`) that the three countries now use for quoting. Most other components are still duplicated per country. Details are in [`context/CURRENT_STATE.md`](context/CURRENT_STATE.md).

On 30 September 2026, during the month-end peak in Mexico, the quote journey degraded in the three countries (incident **INC-0907**, see [`incident/`](incident/)).

The business now asks for two things (see [`context/BUSINESS_REQUESTS.md`](context/BUSINESS_REQUESTS.md)):

1. **BR-01** — Launch a new home insurance product, **Home Essentials**, in the three countries during the first half of 2027, built once and reused.
2. **BR-02** — Assess the feasibility of a **GenAI assistant** inside the quote journey.

## Constraints that apply to every part

| ID | Constraint |
|---|---|
| C1 | **Availability.** The quote journey targets **99.5%** monthly availability per country. A failure or overload in one country must not degrade the others. |
| C2 | **Performance.** A quote is returned to the customer in under **2.5 s at p95**, including month-end peaks (up to 3× normal traffic in Mexico, 2× in Colombia and Chile). |
| C3 | **Reuse.** Shared capabilities are built once and configured per country. Country-specific logic is isolated, so that a change for one country cannot break another. |
| C4 | **Integration.** Every integration with Salesforce or with another cell goes through the integration platform and its API Manager. Exceptions require an approved ADR. |
| C5 | **Security and privacy.** Access is based on validated OIDC tokens. Secrets are never stored in code or configuration files. Personal data (names, national ID numbers, emails, addresses, phone numbers) must not appear in logs or telemetry. Each country's personal data protection law applies, and Legal requires that personal data of one country is not accessible to people or services of another country without a documented basis. |
| C6 | **Salesforce limits.** Each org has a daily API allocation shared by every integration of that country, and each integration has an agreed budget (see `SALESFORCE_CONSTRAINTS.md`). |
| C7 | **Delivery.** Teams work in Scrum with two-week sprints. Changes reach production only through CI/CD pipelines; manual deployments to production are not allowed. |
| C8 | **Third parties.** Nimbus Digital's contract covers Mexico only and is renewed on **31 January 2027**. Meridian can set quality, security and delivery requirements in the renewal. |
| C9 | **Cost.** Choose and size services deliberately, and explain the cost profile of your proposals at a high level. |

## Glossary

| Term | Meaning |
|---|---|
| Cell | A cross-functional team that owns a set of business capabilities end to end. |
| BFF | Backend for frontend: a service that adapts back-end capabilities to the needs of one frontend. |
| Experience / Process / System API | The three layers of the API-led approach (see the ecosystem table). |
| Rating | Calculating the premium of a quote from the product rules and the rating tables. Rating runs in Salesforce. |
| Rating tables | Reference tables used by rating, published once a day (see `SALESFORCE_CONSTRAINTS.md`). |
| Quote-and-buy journey | From the customer's first quote request to the issued policy. |
| Advisor | A Meridian employee or agent who assists customers using the advisor portal. |
