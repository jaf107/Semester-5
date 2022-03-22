import numpy
from PIL import Image

img = Image.open('dataset/all_sample/s1.jpg', 'r')
test_img = Image.open('dataset/test/test_image_1.jpg')
width, height = img.size
width2, h2 = test_img.size

print(img.size, " ", test_img.size)
channels = 3
pixel_values = list(img.getdata())
print(img.size)
# print(img.format)
print(img.mode)

# for i in range(width):
    # print(pixel_values[i])
    # print('\n')
# pixel_values = numpy.array(pixel_values).reshape((width, height, channels))

print("AFTER ORGANIZING")
#
# for i in range(3):
#     print(pixel_values[i])
# print(pixel_values)
