# as-project
Der Service ist aktuell nur rudimentär implementiert und könnte natürlich noch erweitert werden, zum Beispiel durch Parametrisierung der Schnittstelle.
Dabei könnte man sich an bestehende Standards wie z.B. JSON:API orientieren:

Request:
```
/user/{123}?include=comments
```
Response:
```
{
  "data": {
    "type": "user",
    "id": 123,
    "attributes": {
      "name": "Name",
      "userName": "UserName",
    }
    relationships: {
      "comments": {
        "data": [
          {"type": "comment", "id": 1},
          {"type": "comment", "id": 2}
        ]
      }
    }
  "included": [{
    "type": "comment",
    "id": 1
    "attributes": {
      "body": "bla",
      "title": "blub"
    }
  }]
```

Der Vorteil wäre zum einen eine erhöhte Flexibilität bei der Anfrage von Datensätzen, als auch die Verarbeitung der Daten im normalisierten State. Es werden Referenzen genutzt anstatt doppelten Daten.
