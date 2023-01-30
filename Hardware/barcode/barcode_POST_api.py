import requests

url = "http://i8e101.p.ssafy.io/api/iot/barcode"
barcode = input()
data = {
	"kioskId" : 3,
	"product" : barcode,
}

response = requests.post(url, data=data)

print("Response status code:", response.status_code)
print("Response content:", response.content)

