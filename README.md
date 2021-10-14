
<!-- TABLE OF CONTENTS -->
<details>
  <summary>Table of Contents</summary>
  <ol>
    <li>
      <a href="#about-the-service">About The Service</a>
      <ul>
        <li><a href="#libraries-used">libraries used</a></li>
      </ul>
    </li>
    <li>
      <a href="#getting-started">Getting Started</a>
      <ul>
        <li><a href="#installation">Installation</a></li>
      </ul>
    </li>
    <li><a href="#usage">Usage</a></li>
  </ol>
</details>



## About The Service

I take one cross cutting bussiness of bank system .
assume that there are users and they perform transactions. This service is recieving huge data of transactions -so the data is sensitive- performed by user and this data must be saved

this service have 2 method th handle huge data transmitted over http request as a JSON

Methods:
* the first uses multithreding 
* the second uses Spring batch



### libraries used

* Spring Boot
* JPA
* JWT
* Spring Batch
* Multi Threading
* In-memory db(h2)



<!-- GETTING STARTED -->
## Getting Started

First, you should register to the service by using this API (`/user`)

Second, After registration you should log in to get your valid token -valid for one day- to can access the service (`/login `) .

Last, you can Access the method 1 by: (`/transactions/method1`)  and method 2 by: (`/transactions/method2`)
  
  
  
### Installation

1. register by: (`/user`)
2. log in and get your valid token -passed in the response header property 'token'- by: (`/login `)
3. put your token after (`Bearer`)in the header property (`Authorization`) ex: (`BearereyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJtb2hhbWVkMiIsImV4cCI6MTYzNDIzNDE2N30.7yICnBkJmKDI0ABH-RJJI3WmFeiDy2gcBaXhJzplJIxTJrqELVEYFCqJ6OyDF9xPPzHVkdEkgvsV9Prv7pDE9Q`)
   to access any API (`(/transactions/method1 , /transactions/method2)`)



## Usage

You can find full APIs excamples in this postman collection 
Collection Link: [evission-postman-collection](https://github.com)


