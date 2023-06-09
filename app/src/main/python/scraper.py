import requests
from bs4 import BeautifulSoup as bs
lst = []

def main( url):



    try:
        reader = requests.get(url)
        obj = bs(reader.content)
        images = obj.find_all("img")
        for i in images:
            lst.append(i.get("src"))
        return lst


    except TypeError:
        print("error occured")

        return []
