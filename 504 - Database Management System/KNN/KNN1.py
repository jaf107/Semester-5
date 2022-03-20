from PIL import Image

def load_images():
    train_images = [Image.open('dataset/all_sample/s1.jpg'), Image.open('dataset/all_sample/s2.jpg'),
                    Image.open('dataset/all_sample/s3.jpg'), Image.open('dataset/all_sample/s4.jpg'),
                    Image.open('dataset/all_sample/s5.jpg'), Image.open('dataset/all_sample/s6.jpg'),
                    Image.open('dataset/all_sample/s7.jpg'), Image.open('dataset/all_sample/s8.jpg')]

    for t_image in train_images:
        print(t_image.size)
    return train_images



train_image_set = load_images()


