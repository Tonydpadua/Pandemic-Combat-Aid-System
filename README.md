# Pandemic Combat Aid System


Save a new hospital **POST**: localhost:8080/hospitals
```json

{
    "name" : "Hospital Samaritano",
    "address" : "Av. Santa Júlia",
    "cnpj" : "23425345",
    "percentageOfOccupation": 60,
    "localization" : {
        "latitude" : "-17.6184301",
        "longitude" : "-21.8436297"
    },
    "resource" : {
        "items" : [
            {
                "name" : "Doctor",
                "amount" : 4
            },
            {
                "name" : "Nurse",
                "amount" : 6
            },
            {
                "name" : "Ambulance",
                "amount" : 5
            },
            {
                "name" : "Tomograph",
                "amount" : 3
            },
            {
                "name" : "Respirator",
                "amount" : 2
            }
        ]
    }
}
```

Find a hospital by ID **GET**: localhost:8080/hospitals/id
````json
{
    "name" : "Hospital Nossa Senhora das Neves",
    "address" : "R. Etelvina Macedo de Mendonça",
    "cnpj" : "23425345",
    "percentageOfOccupation": 60,
    "localization" : {
        "latitude" : "-17.6184301",
        "longitude" : "-21.8436297"
    },
    "resource" : {
        "items" : [
            {
                "name" : "Doctor",
                "amount" : 4
            },
            {
                "name" : "Nurse",
                "amount" : 6
            },
            {
                "name" : "Ambulance",
                "amount" : 5
            },
            {
                "name" : "Tomograph",
                "amount" : 3
            },
            {
                "name" : "Respirator",
                "amount" : 2
            }
        ]
    }
}

````

Find all hospitals saved **GET**: localhost:8080/hospitals

```json
[
    {
        "id": 1,
        "name": "Hospital Nossa Senhora das Neves",
        "address": "R. Etelvina Macedo de Mendonça",
        "cnpj": "23425345",
        "percentageOfOccupation": 40.0
    },
    {
        "id": 2,
        "name": "Hospital Samaritano",
        "address": "Av. Santa Júlia",
        "cnpj": "23425345",
        "percentageOfOccupation": 60.0
    }
]
```

Find all hospitals with pagination **GET**: localhost:8080/hospitals/page

```json
{
    "content": [
        {
            "id": 2,
            "name": "Hospital Nossa Senhora das Neves",
            "address": "Av. Santa Júlia",
            "cnpj": "23425345",
            "percentageOfOccupation": 92.0
        },
        {
            "id": 1,
            "name": "Hospital Samaritano",
            "address": "Av. Santa Júlia",
            "cnpj": "23425345",
            "percentageOfOccupation": 60.0
        }
    ],
    "pageable": {
        "sort": {
            "sorted": true,
            "unsorted": false,
            "empty": false
        },
        "offset": 0,
        "pageNumber": 0,
        "pageSize": 24,
        "paged": true,
        "unpaged": false
    },
    "last": true,
    "totalPages": 1,
    "totalElements": 2,
    "size": 24,
    "number": 0,
    "sort": {
        "sorted": true,
        "unsorted": false,
        "empty": false
    },
    "first": true,
    "numberOfElements": 2,
    "empty": false
}
```


Update percentage of occupation **PUT**: localhost:8080/hospitals/occupation/id

```json

{"percentageOfOccupation": 80}
```

Negotiate items **POST**: localhost:8080/hospitals/negotiation
```json
[
    {
        "idHospital" : 1,
        "items" : [
            {
                "name": "Doctor",
                "amount": 4
            }
        ]
    },
    {
        "idHospital" : 2,
        "items" : [
            {
                "name": "Tomograph",
                "amount": 1
            }
        ]
    }
]
```

List reports **GET**: localhost:8080/hospitals/reports
```json
{
    "percentageHighOccupation": "50,00 %",
    "percentageLowOccupation": "50,00 %",
    "mediaHospitalItems": {
        "Ambulance": "10,00",
        "Respirator": "10,00",
        "Tomograph": "10,00",
        "Doctor": "10,00",
        "Nurse": "10,00"
    },
    "highOccupationMostTime": "Hospital Nossa Senhora das Neves",
    "lowOccupationMostTime": "Hospital Samaritano",
    "negotiationHistory": [
        "Trade has been made between Hospital Samaritano and Hospital Nossa Senhora das Neves"
    ]
}
```









