# uas-rahman

created with: 
- MVC pattern 

# Database Command

Create new database using mariadb commad line
```css

CREATE DATABASE shop;

CREATE TABLE `produk`  (
  `id_produk` int(11) NOT NULL AUTO_INCREMENT,
  `kode_produk` varchar(32) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `nama_produk` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `satuan` varchar(255) CHARACTER SET latin1 COLLATE latin1_swedish_ci NOT NULL DEFAULT '',
  `harga` int(11) NOT NULL,
  `stock` int(11) NOT NULL,
  `create_date` datetime NOT NULL,
  PRIMARY KEY (`id_produk`) USING BTREE
) ENGINE = InnoDB AUTO_INCREMENT = 4 CHARACTER SET = latin1 COLLATE = latin1_swedish_ci ROW_FORMAT = Dynamic;

SET FOREIGN_KEY_CHECKS = 1;

```

<b>Note</b>: turn off the window scale, transition scale, and animator duration scale when testing

Feel free to see and contribute
- Database
<img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/0.png" height="400"/>


* VIEW
    * Default View
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/01.png" height="400"/>
    * State Input
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/1.png" height="400"/>
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/2.png" height="400"/>
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/3.png" height="400"/>
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/4.png" height="400"/>
    * State Edit
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/5.png" height="400"/>
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/6.png" height="400"/>
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/8.png" height="400"/>
    *  State Print
        * <img src="https://github.com/rahman248/uas-rahman/blob/main/src/assets/10.png" height="400"/>	



# License
MIT License

Copyright (c) 2020 RAHMAN SUWITO

Permission is hereby granted, free of charge, to any person obtaining a copy
of this software and associated documentation files (the "Software"), to deal
in the Software without restriction, including without limitation the rights
to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
copies of the Software, and to permit persons to whom the Software is
furnished to do so, subject to the following conditions:

The above copyright notice and this permission notice shall be included in all
copies or substantial portions of the Software.

THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
SOFTWARE.
