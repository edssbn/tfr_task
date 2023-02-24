# Tech Frame Challenge  

After starting the project the API can be accessed using the url: <http://localhost:8080/api/invoice>  
This is an POST endpoint and in order to properly generate the invoices it requires a JSON object, similar to the one provided below: 

```json
[
    {
        "description": "Bottles of water",
        "quantity": 240,
        "price": 0.25,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Chips",
        "quantity": 38,
        "price": 2.40,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "TV",
        "quantity": 1,
        "price": 760,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Coca Cola",
        "quantity": 77,
        "price": 0.50,
        "discount": 0.10,
        "tax": 18
    },
    {
        "description": "Chocolate Bars",
        "quantity": 38,
        "price": 1.25,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Hand Soup",
        "quantity": 92,
        "price": 3.78,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Fish Meat",
        "quantity": 49,
        "price": 8.30,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "Humus",
        "quantity": 16,
        "price": 2.66,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "White wine",
        "quantity": 18,
        "price": 9.20,
        "discount": 0.02,
        "tax": 18
    },
    {
        "description": "Bananas",
        "quantity": 8,
        "price": 1.25,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Wine",
        "quantity": 22,
        "price": 9.78,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Oil",
        "quantity": 10,
        "price": 8.30,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "Cigarettes",
        "quantity": 89,
        "price": 5.46,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Cookies",
        "quantity": 33,
        "price": 1.34,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Yogurt",
        "quantity": 14,
        "price": 0.66,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "Bleach",
        "quantity": 11,
        "price": 1.23,
        "discount": 0,
        "tax": 22
    },
    {
        "description": "Napkins",
        "quantity": 85,
        "price": 0.21,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Eggs",
        "quantity": 104,
        "price": 0.16,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "Plastic bags",
        "quantity": 398,
        "price": 0.05,
        "discount": 0,
        "tax": 18
    },
    {
        "description": "Aluminum foil",
        "quantity": 21,
        "price": 1.12,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Razor",
        "quantity": 51,
        "price": 8.10,
        "discount": 0,
        "tax": 8
    },
    {
        "description": "Lotion",
        "quantity": 205,
        "price": 12,
        "discount": 0,
        "tax": 22
    }
]
```
