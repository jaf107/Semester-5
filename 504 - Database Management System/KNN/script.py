import numpy
from PIL import Image

img = Image.open('dataset/sample1/snow1.jpg', 'r')
width, height = img.size
channels = 3
pixel_values = list(img.getdata())
print(img.size)
# print(img.format)
print(img.mode)

for i in range(9):
    print(pixel_values[i])
pixel_values = numpy.array(pixel_values).reshape((width, height, channels))

print("AFTER ORGANIZING")

for i in range(3):
    print(pixel_values[i])
# print(pixel_values)