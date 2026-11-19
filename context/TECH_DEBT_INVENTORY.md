# Technical debt inventory — Direct Digital Channel

> Compiled by the cell on 15 September 2026. Effort estimates are the team's rough estimates in person-weeks (pw).

| ID | Item | Countries | Evidence or signal | Team estimate |
|---|---|---|---|---|
| TD-01 | Three diverged copies of the quote Process API (about 70% duplicated logic) | All | A pricing rule change in 2026-Q2 needed three releases and 19 days | 8–10 pw to consolidate into one Process API with country configuration |
| TD-02 | `mx-quote-bff` issues policies by calling the MX org directly | MX | Not visible in the API Manager; the security exception for it expired in June 2026 | 4–6 pw to route issuance through `sf-policy-sapi-mx` |
| TD-03 | Two BFFs (`channel-bff` and `mx-quote-bff`) | All | Every channel feature is built twice for Mexico | 6–8 pw to move the Mexican journeys to `channel-bff`, journey by journey |
| TD-04 | `web-quote-ui-mx` on Angular 15 | MX | Out of support; no security patches since 2024 | 5–7 pw to upgrade, or 8–12 pw to move Mexico to `web-quote-ui` with country configuration |
| TD-05 | No contract tests between frontends, BFFs and Experience APIs | All | Four breaking changes found in UAT in 2026; one reached production (Chile, May 2026) | 3–4 pw |
| TD-06 | Nimbus deploys from its own CI with a shared Meridian credential | MX | The credential has not been rotated since 2024; no security scanning in Nimbus's CI | 3–5 pw to move Nimbus components to Meridian pipelines |
| TD-07 | No SLOs; alerts only on CPU and memory | All | Incidents are usually reported by the contact center before any alert fires | 2–3 pw |
| TD-08 | Quote drafts kept indefinitely in the channel databases (they contain personal data) | CO, CL (MX unknown) | Raised by Legal in the 2026 privacy review | 1–2 pw |
| TD-09 | Unit test coverage below the 70% standard | CL, MX | CL 48%; Nimbus does not report coverage | Ongoing |
| TD-10 | No feature flags; every release enables its features for a whole country at once | All | Three rollbacks in 2026 | 2–3 pw |
| TD-11 | `channel-bff` in Chile reads two secrets from application settings instead of Key Vault | CL | Medium finding in the 2026 penetration test | 0.5 pw |
| TD-12 | `mx-quote-bff` on Java 17 and Spring Boot 2.7 | MX | Spring Boot 2.7 is out of open-source support | 3–4 pw (Nimbus) |
