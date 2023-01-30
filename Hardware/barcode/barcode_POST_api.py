import requests

url = "http://13.125.42.187/api/iot/barcode"
barcode = input()
data = {
	"kioskId" : 3,
	"product" : barcode,
}

response = requests.post(url, data=data)

print("Response status code:", response.status_code)
print("Response content:", response.content)
