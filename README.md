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
    "percentageHighOccupation": "75,00 %",
    "percentageLowOccupation": "25,00 %",
    "mediaHospitalItems": {
        "Ambulance": "2,00",
        "Respirator": "2,00",
        "Tomograph": "2,00",
        "Doctor": "2,00",
        "Nurse": "2,00"
    },
    "highOccupationMostTime": "Hospital Nossa Senhora das Neves 2",
    "lowOccupationMostTime": "Hospital Nossa Senhora das Neves 4"
}
```









