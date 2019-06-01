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


#### Test managment endpoints

##### GET /api/v1/manage/tests/redactor/{id}
Get all test for redactor

```json
[
  {
    "testId": 0,
    "testName": "name",
    "language": ["PL", "EN"],
    
    "testOwner": {
      // user data for owner
    },
    
    "questions": [
      // question definitions
    ]
  }
]

```

where question definition is one of following:

```json
{
  "questionId": 0,
  "questionNumber": 0,
  "questionDesc": "This is your question",
  "questionLang": "PL or EN",
  "questionType": "OPEN|SCALE|NUMBER|CHOICE",
  
  // if question is scale:
  "minVal": 0,
  "maxVal": 10,
  
  // if question is choice:
  "choices": ["First", "Second", "etc"]
}
```

##### GET /api/v1/manage/tests
Get all tests

##### GET /api/v1/manage/tests/id/{id}
Get test by its id (stucture similar to previous, but only one object, not list)

##### GET /api/v1/manage/tests/me
Get all my test when logged as redactor

##### DELETE /api/v1/manage/tests/id/{id}
Remove test with given id and all its questions


##### POST /api/v1/manage/tests/rename
Rename test:

```json
{
  "testId": 0,
  "newName": "New test name"
}
```

##### GET /api/v1/manage/tests/add?name={testname}
Add new empty test, returns test object

##### DELETE /api/v1/manage/tests/modify/{testId}/{questionId}
Remove question with id from test

##### POST /api/v1/manage/tests/modify/{testId}
Add question to test

```json
{
  "questionNumber": 0,
  "questionDesc": "this is your question?",
  "questionLang": "PL or EN",
  "questionType": "OPEN|SCALE|NUMBER|CHOICE",
  "questionData": "metadata string described below"
}
```

Metadata string is:

- If question is open/number:            "|"
- If question is scale:  "minVal|maxVal" e.g. "0|10"
- If question is choice: "first|second|third|last"

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

##### GET /api/v1/manage/positions/{lang}
Get positions that have tests in given language

##### DELETE /api/v1/manage/positions/{id}
Delete position with given ID

##### PUT /api/v1/manage/positions/{id}/activate
Activate position with given ID

##### PUT /api/v1/manage/positions/{id}/deactivate
Deactivate position with given ID

##### GET /api/v1/manage/positions/create?id={positionName}
Generate new position, return serialized object with filled id

##### PUT /api/v1/manage/positions/{positionId}/{testId}
Add test to position

##### DELETE /api/v1/manage/positions/{positionId}/{testId}
Remove test from position


##### POST /api/v1/manage/answer
Answer to test

```json
{
  "testId": 0,
  "positionId": 0,
  "language": "EN",
  "answers": [
    {
      "questionId": 0,
      "answer": "My Answer"
    },
    {
      // ...
    }
  ]
}
```