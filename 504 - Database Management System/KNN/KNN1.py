import math

from PIL import Image


def load_images():
    train_images = [Image.open('dataset/all_sample/s1.jpg'), Image.open('dataset/all_sample/s2.jpg'),
                    Image.open('dataset/all_sample/s3.jpg'), Image.open('dataset/all_sample/s4.jpg'),
                    Image.open('dataset/all_sample/s5.jpg'), Image.open('dataset/all_sample/s6.jpg'),
                    Image.open('dataset/all_sample/s7.jpg'), Image.open('dataset/all_sample/s8.jpg')]

    # for t_image in train_images:
    #     print(t_image.size)
    return train_images


def square_of_dif(param, param1):
    dif = abs(param - param1)
    return dif * dif


def euclidean(train_img, test_image):
    wid_tr, hei_tr = train_img.size
    wid_te, hei_te = test_image.size

    width, height = min(wid_te, wid_tr), min(hei_tr, hei_te)
    value = 0.0
    for w in width:
        for h in height:
            value += square_of_dif(train_img[w][h], test_image[w][h])

    return math.sqrt(value)


def calculate_nearest(train_img):
    test_image_1 = Image.open('dataset/all_sample/test_image_1.jpg','r')
    t_width, t_height = test_image_1.size
    knn_value = euclidean(train_img, test_image_1)
    print(knn_value)


train_image_set = load_images()
calculate_nearest(train_image_set[0])
