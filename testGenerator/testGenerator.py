

import requests
from bs4 import BeautifulSoup

def scrape_site(url):
    """Scrape image and page URLs from a given webpage."""
    response = requests.get(url)
    soup = BeautifulSoup(response.text, 'html.parser')
    image_urls = [img['src'] for img in soup.find_all('img') if 'src' in img.attrs]
    page_urls = [a['href'] for a in soup.find_all('a') if 'href' in a.attrs]
    return image_urls, page_urls

# Example usage:
image_urls, page_urls = scrape_site('https://www.ynet.co.il/')


with open('test_urls.txt', 'w') as f:
    for url in image_urls + page_urls:
        f.write(url + '\n')
