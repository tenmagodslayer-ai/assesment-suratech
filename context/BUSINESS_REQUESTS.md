# Business requests

## BR-01 — Home Essentials

| Item | Detail |
|---|---|
| Sponsor | Regional Director of Home Insurance |
| What | A simplified home insurance product sold 100% digitally, through the customer web journey and through advisors |
| Launch dates | Mexico **30 April 2027** · Colombia **31 May 2027** · Chile **30 June 2027** |
| Customer experience | A quote in no more than five questions, purchase in the same session, and the policy issued and sent to the customer in under three minutes |
| Payments | Existing payment methods, through the Payments Integration cell |
| Documents | Policy documents sent through the Communications cell |
| Advisors | Advisors must be able to quote and sell the product from day one |
| Product rules | About 80% common to the three countries (see `SALESFORCE_CONSTRAINTS.md`) |
| Expected volume | About 15,000 quotes per month per country during the first quarter after launch |

The sponsor's words: *"We cannot afford to build this three times. Build it once, and make the next product cheaper."*

**Team estimates** (rough, prepared by the internal developers in September 2026):

| Approach | Estimate for the product | Notes from the team |
|---|---|---|
| Build it in each country's current stack | 75–90 person-weeks | Three frontends, two BFFs, three Process APIs. Mexico by Nimbus |
| Build it once and configure it per country | 45–55 person-weeks | Requires the three countries to be served by the same frontend, BFF and Process API for this product. The team believes at least part of TD-01, TD-03 and TD-04 is needed first, but has not decided which part |

## BR-02 — GenAI assistant in the quote journey

| Item | Detail |
|---|---|
| Sponsor | Chief Commercial Officer |
| What | A conversational assistant inside the quote journey that answers the customer's questions about coverages and pre-fills the quote from the conversation |
| Requested start | Mexico, Q1 2027, then the other countries |
| Sponsor's ask | *"I need a yes or a no, and a date."* |

**Evidence provided by the sponsor**

| Data | MX | CO | CL |
|---|---|---|---|
| Quote abandonment rate (2026-Q3) | 68% | 61% | 64% |
| Contact center calls per month about coverage questions | ≈ 8,100 | ≈ 6,900 | ≈ 3,000 |

- In an exit survey of the quote journey (≈ 2,300 responses, three countries), **41%** of the customers who abandoned said they did not understand the coverages.
- The average cost of a contact center call is **USD 4.10**.

**Known constraints**

- An LLM service is available in Meridian's Azure tenant. Security has approved it for **internal pilots only**; customer-facing use requires a new approval.
- Compliance warns that, in the three countries, telling a customer which coverage they should buy can be treated as regulated advice. The assistant must not recommend.
- Policy wordings and product sheets exist as PDF documents in Spanish, one set per country and product. They are updated about four times a year.
