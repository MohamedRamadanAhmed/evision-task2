
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

I take cross cutting bussiness of bank system .
assume that there are users and they perform transactions. This service is recieving huge date of transactions -so the date is sensitive- performed by user and this data must be saved

this service have 2 method th handle huge data transmitted over http request as a JSON

Methods:
* the first uses multithreding 
* the second uses Spring batch

Of course, no one template will serve all projects since your needs may be different. So I'll be adding more in the near future. You may also suggest changes by forking this repo and creating a pull request or opening an issue. Thanks to all the people have contributed to expanding this template!

Use the `BLANK_README.md` to get started.

<p align="right">(<a href="#top">back to top</a>)</p>



### libraries used

* Spring Boot
* JPA
* JWT
* Spring Batch
* Multi Threading
* In-memory db(h2)
* Unit-testing





<!-- GETTING STARTED -->
## Getting Started

first you should register to the service by using this API  
 ```sh
  /user
  ```
After you register you should log in to get your valid token -valid for one day- to can access the service.
```sh
  /login  
  ```
last you can Access the method 1 by: /transactions/method1 and method 2 by: /transactions/method2
  
### Installation

1. register by: /user
2. log in and get your valid token -passed in the response header property 'token'- by: /login
3. put your token in the header property 'Authorization' to access any API (method1/method2)

## Usage

You can find full APIs excamples in this postman collection 

