import requests
from bs4 import BeautifulSoup as bs
lst = []

def main( url):
    reader = requests.get(url)
    obj = bs(reader.content)
    images = obj.find_all("img")


    try:
        for i in images:
            lst.append(i.get("src"))

    except TypeError:
        print("error occured")

    return lst
