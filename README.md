# helio-providers-web

A set of data providers for data on the web


### HttpProvider

````json


{
    "source" : "https://github.com/helio-ecosystem/helio-providers-web/releases/download/v0.1.3/helio-providers-web-0.1.3.jar",
    "clazz": "helio.providers.HttpProvider",
    "type": "PROVIDER"
  }
````

The configuration information that can be passed to this provider are the following fields:

 * `url` the URL where the data is
 * `method` the method (GET, POST, PUT) used for fetching the data
 * `body` the data to enclose in the body
 * `headers` the headers that will be used by the request
