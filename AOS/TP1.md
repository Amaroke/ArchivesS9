# Exercice 1

## 1
`
<Add xmlns="http://tempuri.org/">
    <intA>int</intA>
    <intB>int</intB>
</Add>  
`

`
<AddResponse xmlns="http://tempuri.org/">
    <AddResult>int</AddResult>
</AddResponse>
`

## 2
### Divide

`
<Divide xmlns="http://tempuri.org/">
    <intA>int</intA>
    <intB>int</intB>
</Divide>
`

`
<DivideResponse xmlns="http://tempuri.org/">
    <DivideResult>int</DivideResult>
</DivideResponse>
`

### Multiply

`
<Multiply xmlns="http://tempuri.org/">
    <intA>int</intA>
    <intB>int</intB>
</Multiply>
`

`
<MultiplyResponse xmlns="http://tempuri.org/">
    <MultiplyResult>int</MultiplyResult>
</MultiplyResponse>
`

### Subtract

`
<Subtract xmlns="http://tempuri.org/">
    <intA>int</intA>
    <intB>int</intB>
</Subtract>
`

`
<SubtractResponse xmlns="http://tempuri.org/">
    <SubtractResult>int</SubtractResult>
</SubtractResponse>
`

## 3

Add, Substract, Multiply and Divide, avec à chaque fois deux int en paramètres.

Les réponses sont AddResponse, SubstractResponse, MultiplyResponse et DivideResponse, avec à chaque fois un int en résultat.

# Exercice 2

## 1

`http://api.geonames.org/countryInfo?country=FR&username=randomaccount1234`

`
<geonames>
<country>
<countryCode>FR</countryCode>
<countryName>France</countryName>
<isoNumeric>250</isoNumeric>
<isoAlpha3>FRA</isoAlpha3>
<fipsCode>FR</fipsCode>
<continent>EU</continent>
<continentName>Europe</continentName>
<capital>Paris</capital>
<areaInSqKm>547030.0</areaInSqKm>
<population>66987244</population>
<currencyCode>EUR</currencyCode>
<languages>fr-FR,frp,br,co,ca,eu,oc</languages>
<geonameId>3017382</geonameId>
<west>-5.14127657354623</west>
<north>51.0889894407743</north>
<east>9.56009360694225</east>
<south>41.3645589826522</south>
<postalCodeFormat>#####</postalCodeFormat>
</country>
</geonames>
`

## 2

`http://api.geonames.org/countryInfoCSV?country=FR&username=randomaccount1234`

`
iso alpha2	iso alpha3	iso numeric	fips code	name	capital	areaInSqKm	population	continent	languages	currency	geonameId
FR	FRA	250	FR	France	Paris	547030.0	66987244	EU	fr-FR,frp,br,co,ca,eu,oc	EUR	3017382
`

`http://api.geonames.org/countryInfoJSON?country=FR&username=randomaccount1234`

`
{
    "geonames": [
        {
            "continent": "EU",
            "capital": "Paris",
            "languages": "fr-FR,frp,br,co,ca,eu,oc",
            "geonameId": 3017382,
            "south": 41.3645589826522,
            "isoAlpha3": "FRA",
            "north": 51.0889894407743,
            "fipsCode": "FR",
            "population": "66987244",
            "east": 9.56009360694225,
            "isoNumeric": "250",
            "areaInSqKm": "547030.0",
            "countryCode": "FR",
            "west": -5.14127657354623,
            "countryName": "France",
            "postalCodeFormat": "#####",
            "continentName": "Europe",
            "currencyCode": "EUR"
        }
    ]
}
`

## 3

`http://api.geonames.org/countryInfo?username=randomaccount1234`

`http://api.geonames.org/countryInfoCSV?username=randomaccount1234`

`http://api.geonames.org/countryInfoJSON?username=randomaccount1234`

## 4

`http://api.geonames.org/children?geonameId=3175395&username=randomaccount1234`

`http://api.geonames.org/countryCode?lat=47.03&lng=10.2&username=randomaccount1234`

# Exercice 3

Même chose mais avec Postman.

# Exercice 4

## 1

``` 
NDFDgen:
Input:
latitude (type: decimal)
longitude (type: decimal)
product (type: string)
startTime (type: dateTime)
endTime (type: dateTime)
Unit (type: string)
weatherParameters (type: weatherParametersType)
Output:
dwmlOut (type: string)

NDFDgenLatLonList:
Input:
listLatLon (type: string)
product (type: string)
startTime (type: dateTime)
endTime (type: dateTime)
Unit (type: string)
weatherParameters (type: weatherParametersType)
Output:
dwmlOut (type: string)

LatLonListSubgrid:
Input:
lowerLeftLatitude (type: decimal)
lowerLeftLongitude (type: decimal)
upperRightLatitude (type: decimal)
upperRightLongitude (type: decimal)
resolution (type: decimal)
Output:
listLatLonOut (type: string)

LatLonListLine:
Input:
endPoint1Lat (type: decimal)
endPoint1Lon (type: decimal)
endPoint2Lat (type: decimal)
endPoint2Lon (type: decimal)
Output:
listLatLonOut (type: string)

LatLonListZipCode:
Input:
zipCodeList (type: string)
Output:
listLatLonOut (type: string)

LatLonListCityNames:
Input:
displayLevel (type: integer)
Output:
listLatLonOut (type: string)

LatLonListSquare:
Input:
centerPointLat (type: decimal)
centerPointLon (type: decimal)
distanceLat (type: decimal)
distanceLon (type: decimal)
resolution (type: decimal)
Output:
listLatLonOut (type: string)

CornerPoints:
Input:
sector (type: string)
Output:
listLatLonOut (type: string)

GmlLatLonList:
Input:
listLatLon (type: string)
requestedTime (type: dateTime)
featureType (type: string)
weatherParameters (type: weatherParametersType)
Output:
dwGmlOut (type: string)

GmlTimeSeries:
Input:
listLatLon (type: string)
startTime (type: dateTime)
endTime (type: dateTime)
compType (type: string)
featureType (type: string)
propertyName (type: string)
Output:
dwGmlOut (type: string)

NDFDgenByDay:
Input:
latitude (type: decimal)
longitude (type: decimal)
startDate (type: date)
numDays (type: integer)
Unit (type: string)
format (type: string)
Output:
dwmlByDayOut (type: string)

NDFDgenByDayLatLonList:
Input:
listLatLon (type: string)
startDate (type: date)
numDays (type: integer)
Unit (type: string)
format (type: string)
Output:
dwmlByDayOut (type: string)
``` 

## 2

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl">
    <soapenv:Header/>
    <soapenv:Body>
        <web:LatLonListZipCodeRequest>
            <web:zipCodeList>75001</web:zipCodeList>
        </web:LatLonListZipCodeRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

## 3

```xml	
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl">
    <soapenv:Header/>
    <soapenv:Body>
        <web:NDFDgenByDayRequest>
            <web:latitude>42.3601</web:latitude>
            <web:longitude>-71.0589</web:longitude>
            <web:startDate>2019-11-12</web:startDate>
            <web:numDays>7</web:numDays>
            <web:Unit>e</web:Unit>
            <web:format>24 hourly</web:format>
        </web:NDFDgenByDayRequest>
    </soapenv:Body>
</soapenv:Envelope>
```

## 4

```xml
<soapenv:Envelope xmlns:soapenv="http://schemas.xmlsoap.org/soap/envelope/" xmlns:web="https://graphical.weather.gov/xml/DWMLgen/wsdl/ndfdXML.wsdl">
    <soapenv:Header/>
    <soapenv:Body>
        <web:NDFDgenByDayLatLonListRequest>
            <web:listLatLon>42.3601,-71.0589</web:listLatLon>
            <web:startDate>2019-11-12</web:startDate>
            <web:numDays>7</web:numDays>
            <web:Unit>e</web:Unit>
            <web:format>24 hourly</web:format>
        </web:NDFDgenByDayLatLonListRequest>
    </soapenv:Body>
</soapenv:Envelope>
```
