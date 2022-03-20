from PIL import Image 

img = Image.open('dataset/sample1/snow1.jpg')

print(img.format)
print(img.mode)