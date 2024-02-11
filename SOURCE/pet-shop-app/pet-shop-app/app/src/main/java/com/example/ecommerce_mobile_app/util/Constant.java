package com.example.ecommerce_mobile_app.util;

import java.util.Arrays;
import java.util.List;

public class Constant {
    private Constant() {
        // do nothing
    }


    public static String STATUS_SUCCESS = "OK";
    public static String ERROR_MSG_FAIL = "Có lỗi xảy ra! Vui lòng thử lại";

    public static final class Type {
        private Type() {

        }

        public static final String PRODUCT = "PRODUCT";
        public static final String SERVICE = "SERVICE";
    }

    public static final List<String> IMAGES_SERVICE = Arrays.asList(
            "https://daihocthuyhanoi.edu.vn/assets/uploads/news/images/cho-meo-1-1040-1.jpg",
            "https://daihocthuyhanoi.edu.vn/assets/uploads/news/images/cho-meo-1-1040-1.jpg",
            "https://static.hieuluat.vn/uploaded/Images/Original/2021/11/03/cho-meo-co-phai-gia-suc_0311094711.jpg",
            "https://nld.mediacdn.vn/zoom/700_438/2018/2/8/dog-cat-15180764673761723245972.jpg",
            "https://dreampet.com.vn/wp-content/uploads/2021/03/benh-truyen-nhiem-o-cho-meo-2.jpg",
            "https://image.vtc.vn/files/minhhai/2016/09/07/cho-meo-1042.jpg",
            "https://cms.luatvietnam.vn/uploaded/Images/Original/2018/12/19/nuoi-cho-meo_1912222544.jpg",
            "https://tuvanluat.vn/maytech_data/uploads/2018/08/Nu%C3%B4i-ch%C3%B3-m%C3%A8o-e1557224154937-800x500_c.jpg",
            "https://tuvanluat.vn/maytech_data/uploads/2018/08/Nu%C3%B4i-ch%C3%B3-m%C3%A8o-e1557224154937-800x500_c.jpg",
            "https://azpet.com.vn/wp-content/uploads/2020/09/shutterstock_1779958550_huge.jpg"
    );
}
