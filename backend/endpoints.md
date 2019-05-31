#### User/Redactor managment endpoints

##### GET /api/v1/manage/redactors

Get all users

```json
[
  {
    "userId": 0,
    "userName": "userName",
    "userRole": "USER|REDACTOR|MODERATOR"
  }
  // ...
]
```

##### PUT /api/v1/manage/redactors/{id}
Make user with id redactor

##### DELETE /api/v1/manage/redactors{id}
Make redactor with id user

##### POST /api/v1/manage/users
Update user with structure like in get user endpoint (add field password set to new password or explicit to null)

##### DELETE /api/v1/manage/users/{id}
Remove user with given id

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