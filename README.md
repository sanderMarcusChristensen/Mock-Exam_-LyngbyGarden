# Task 1 endpoint test MOCK: 
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




