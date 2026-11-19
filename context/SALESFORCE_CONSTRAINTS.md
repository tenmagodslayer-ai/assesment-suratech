# Salesforce — constraints for the Direct Digital Channel

> Fictional figures agreed between the Salesforce platform team and the Direct Digital Channel cell.

## Topology

- One org per country: **MX**, **CO** and **CL**. Each org holds the CRM and the policy core.
- Products are configured separately in each org. About **80%** of the Home Essentials product rules are common to the three countries; the rest (taxes, mandatory coverages, document requirements, consent texts) differ by country.
- The Salesforce platform team owns the **System APIs** (`sf-policy-sapi-mx`, `sf-policy-sapi-co`, `sf-policy-sapi-cl`). Other teams consume them; under C4 they do not call Salesforce directly.

## Availability and latency

| Metric | Value |
|---|---|
| Availability (monthly, each org) | ≈ **97%**. Unavailability is unplanned and usually comes in windows of 5 to 90 minutes. |
| Rating request (one quote) | p50 ≈ 350 ms · p95 ≈ 1.1 s under normal load |
| Rating table query | p50 ≈ 80 ms · p95 ≈ 300 ms under normal load |
| Policy issuance | p50 ≈ 1.8 s · p95 ≈ 4 s |
| Request rate | The platform team asks each integration user to stay below **40 requests per second** per org. Above that, latency degrades for every integration of the org. |

## Rating tables

- Each product uses **six** rating tables.
- Rating tables are published once a day at **05:00 UTC**, with a version identifier.
- Business accepts prices calculated with tables up to **24 hours** old, provided the quote records and shows the table version used.

## API allocation

| Org | Org-wide daily allocation (rolling 24 h) | Direct Digital Channel budget (rolling 24 h) | Other main consumers |
|---|---|---|---|
| MX | 2,000,000 calls | 250,000 calls | Payments Integration, contact center, partner reporting |
| CO | 2,400,000 calls | 300,000 calls | Payments Integration, contact center |
| CL | 1,600,000 calls | 150,000 calls | Payments Integration, contact center |

- Every REST request counts as one call, regardless of how many records it carries.
- When the org-wide allocation is exceeded, Salesforce rejects requests from **all** integrations of that org with `403 REQUEST_LIMIT_EXCEEDED` until usage drops.
- To protect payment processing, the Payments Integration squad **pauses its consumers** when the org-wide usage reaches **85%**, and resumes them below 80% or when the platform team grants a temporary increase.
- The platform team reviews consumption per integration user every month. There are no automated alerts per integration user today.

## Authentication

- Integrations authenticate with OAuth 2.0 client credentials through an external client app registered in each org. Credentials are issued by the Salesforce platform team to the owner of the consuming API.
