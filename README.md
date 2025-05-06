# 🔖 LeoParser Service

---

## 🚀 Run example


```bash
java -jar leoparser.jar \
  --LEAGUE_LIST=2 \
  --MATCH_LIST_IN_LEAGUE=2 \
  --BETS_PER_MATCH=2 \
  --THREADS=3
```
* File in root project 
* Use VPN if no packets 
`ping leon.bet`

| Parameter        | Description                            | Default Value |
|------------------|----------------------------------------|-------------|
| LEAGUE_LIST      | Number of top leagues to process       | 2           |
| MATCH_LIST_IN_LEAGUE | Number of matches per league       | 2           |
| BETS_PER_MATCH   | Number of bets per match               | 2           |
| THREADS          | Number of threads for parallel parsing | 3           |
