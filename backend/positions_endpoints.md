#### Position managment endpoints

##### GET /api/v1/manage/positions

Get all available position with status

```json
[
  {
    "positionId": 0,
    "positionName": "Test",
    "isActive": true,
    "tests": []
  },
  {
    // ...
  }
]
```

##### DELETE /api/v1/manage/positions/{id}
Delete position with given ID

##### PUT /api/v1/manage/positions/{id}/activate
Activate position with given ID

##### PUT /api/v1/manage/positions/{id}/deactivate
Deactivate position with given ID

##### GET /api/v1/manage/positions/create?id={positionName}
Generate new position, return serialized object with filled id