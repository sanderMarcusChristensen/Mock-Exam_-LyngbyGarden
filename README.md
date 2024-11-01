# Task 1: Build a REST Service
## Mock - data - endpoint test in http-fil: 
### 1. GET http://localhost:7007/api/mock (get all )

HTTP/1.1 200 OK
Date: Fri, 01 Nov 2024 14:21:14 GMT
Content-Type: application/json
Content-Length: 152

[
{
"id": 1,
"plantType": "Rose",
"name": "Albertine",
"price": 400.0,
"maxHeight": 199
},
{
"id": 2,
"plantType": "Bush",
"name": "Aronia",
"price": 200.0,
"maxHeight": 169
}
]
Response file saved.
> 2024-11-01T152115.200.json

Response code: 200 (OK); Time: 352ms (352 ms); Content length: 152 bytes (152 B)


### 2. GET http://localhost:7007/api/mock/1 (get with id )

HTTP/1.1 200 OK
Date: Fri, 01 Nov 2024 14:23:16 GMT
Content-Type: application/json
Content-Length: 76

{
"id": 1,
"plantType": "Rose",
"name": "Albertine",
"price": 400.0,
"maxHeight": 199
}
Response file saved.
> 2024-11-01T152316.200.json

Response code: 200 (OK); Time: 17ms (17 ms); Content length: 76 bytes (76 B)

### 3. POST http://localhost:7007/api/mock (add)

HTTP/1.1 201 Created
Date: Fri, 01 Nov 2024 14:26:09 GMT
Content-Type: application/json
Content-Length: 80

{
"id": 4,
"plantType": "Spring Bloom",
"name": "Tulip",
"price": 180.0,
"maxHeight": 300
}
Response file saved.
> 2024-11-01T152609.201.json

Response code: 201 (Created); Time: 61ms (61 ms); Content length: 80 bytes (80 B)

### 4. GET http://localhost:7007/api/mock/type/Rose (get type)

HTTP/1.1 200 OK
Date: Fri, 01 Nov 2024 14:29:59 GMT
Content-Type: application/json
Content-Length: 78

[
{
"id": 1,
"plantType": "Rose",
"name": "Albertine",
"price": 400.0,
"maxHeight": 199
}
]
Response file saved.
> 2024-11-01T152959.200.json

Response code: 200 (OK); Time: 314ms (314 ms); Content length: 78 bytes (78 B)


# Task 2: REST Errorhandling

- 200 ok = Successfully
- 201 = Successfully created 
- 400 = Bad request 
- 401 = Unauthorized (Needs to be admin to)
- 404 = Not found 
- 500 : Server error 

| HTTP method | REST Ressource          | Exceptions and status(es)    |
|-------------|-------------------------|------------------------------|
| GET         | `/api/mock`             | 200, 500                     |
| GET         | `/api/mock/{id}`        | 200, 404 (can't fin id), 500 |
| GET         | `/api/mock/type/{type}` | 200, 400, 404, 500           |
| POST        | `/api/mock`             | 201, 400, 401, 500           |


GET http://localhost:7007/api/mock/10

HTTP/1.1 400 Bad Request
Date: Fri, 01 Nov 2024 16:14:27 GMT
Content-Type: application/json
Content-Length: 58

{
"id": [
{
"message": "Not a valid id",
"args": {},
"value": 10
}
]
}
Response file saved.
> 2024-11-01T171428.400.json

Response code: 400 (Bad Request); Time: 526ms (526 ms); Content length: 58 bytes (58 B)

GET http://localhost:7007/api/mock/type/SomthingNotThere

HTTP/1.1 404 Not Found
Date: Fri, 01 Nov 2024 16:16:07 GMT
Content-Type: application/json
Content-Length: 62

{
"status": 404,
"message": "plant with that plantType not found"
}
Response file saved.
> 2024-11-01T171607.404.json

Response code: 404 (Not Found); Time: 14ms (14 ms); Content length: 62 bytes (62 B)


#### request: POST localhost:7007/api/mock
Content-Type: application/json

{
"name": "",
"plantType": "Spring Bloom",
"price": 180.00,
"maxHeight": 200
}

#### response : POST http://localhost:7007/api/mock

HTTP/1.1 400 Bad Request
Date: Fri, 01 Nov 2024 16:22:06 GMT
Content-Type: application/json
Content-Length: 46

{
"status": 400,
"message": "Plant name is empty"
}
Response file saved.
> 2024-11-01T172206.400.json

Response code: 400 (Bad Request); Time: 16ms (16 ms); Content length: 46 bytes (46 B)

# Task 3: Streams and Generics

3.4: Stream API is inspired by functional programming





