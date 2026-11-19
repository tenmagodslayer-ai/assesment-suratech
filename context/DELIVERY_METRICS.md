# Delivery and quality metrics — 2026-Q3

> Source: Azure Pipelines and the cell's dashboards for internal components; monthly reports from Nimbus Digital for Mexico.

| Metric | CO | CL | MX |
|---|---|---|---|
| Deployment frequency to production | Weekly | Every two weeks | Monthly |
| Lead time for changes (median, commit to production) | 6 days | 11 days | 24 days |
| Change failure rate | 12% | 18% | 31% |
| Time to restore service (median) | 3 h | 5 h | 9 h |
| Unit test line coverage | 71% | 48% | Not reported |
| Contract tests | None | None | None |
| Performance tests before releases | Ad hoc | None | None |
| SAST | Yes | Yes | Unknown |
| Dependency scanning (SCA) | Yes | No | Unknown |
| Secret scanning | No | No | Unknown |
| Quote journey availability (synthetic checks) | 99.2% | 98.9% | 98.1% |

Notes:
- Internal pull requests require one approval. Nimbus reviews its own pull requests.
- There is no common definition of done across the internal team and Nimbus.
