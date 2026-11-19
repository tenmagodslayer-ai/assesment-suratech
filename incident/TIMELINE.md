# Changes and scheduled events before INC-0907

> Source: deployment notifications, the change calendar and the job scheduler. Times in UTC.

| Time (UTC) | Event | Owner | Source |
|---|---|---|---|
| 2026-09-28 19:10 | `channel-bff` 5.2.0 deployed to Colombia and Chile: text changes in the quote screens and minor dependency updates | Internal | Azure Pipelines |
| 2026-09-29 23:40 | `quote-papi-mx` 3.8.0 deployed to production in Mexico | Nimbus Digital | Deployment notification from Nimbus CI |
| 2026-09-30 15:00 | Monthly funnel report starts on `pg-channel-co`. Runs on the last day of every month (it also ran on 31 August without incident) | Data & Analytics | Job scheduler |
| 2026-09-30 16:00 | Planned rotation of the identity provider's token signing key, announced on 23 September | Identity team | Change calendar |

## Release notes received with the `quote-papi-mx` 3.8.0 notification

> **quote-papi-mx 3.8.0** — Nimbus Digital
>
> - **Resilience:** Salesforce calls now retry up to 3 times on timeout or 5xx, so that transient errors do not reach the customer.
> - **Pricing accuracy:** removed the in-memory rating table cache. Rating tables are now read from Salesforce on every quote to always use fresh prices (requested by the MX pricing team).
> - Upgraded the HTTP connector.
>
> Tested in UAT. No configuration changes required.
