package com.example.data;

import java.util.List;

public class MainPageInfo {

    /**
     * errNo : 0
     * result : {"Carousel":[{"url":"https://a.zhulong.com/poster/newjump/?plan_id=2913&prof=bim&placename_id=177&show_flag=1","img":"https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4166&prof=bim&placename_id=178&show_flag=1","img":"https://f.zhulong.com/poster/202005/26/53/1009539y1kdqqbjpclwkwd_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202005/26/53/1009539y1kdqqbjpclwkwd_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4173&prof=bim&placename_id=179&show_flag=1","img":"https://f.zhulong.com/poster/202004/23/48/093548xi2b6ogxftr43puk_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202004/23/48/093548xi2b6ogxftr43puk_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=4160&prof=bim&placename_id=180&show_flag=1","img":"https://f.zhulong.com/poster/202001/16/59/175259k07528t7faidza4i_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202001/16/59/175259k07528t7faidza4i_300_600_550_310.jpg?t=20190710"},{"url":"https://a.zhulong.com/poster/newjump/?plan_id=2501&prof=bim&placename_id=182&show_flag=1","img":"https://f.zhulong.com/poster/202001/07/49/113849xujupsagmeyjwij0_300_600_550_310.jpg?t=20190710","thumb":"https://f.zhulong.com/poster/202001/07/49/113849xujupsagmeyjwij0_300_600_550_310.jpg?t=20190710"}],"live":[{"live_id":"16972","teacher_name":"徐钢","coverImgUrl":"https://newoss.zhulong.com/edu/202005/18/16/0957168tdjlfitcvq9vxyx.jpg","activityName":"BIM考试二级结构直播答疑-第六次","correlative_lessons":"4971,9509","startTime":"1591530640","endTime":"1591535454","teacher_uid":"11442868","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/44/28/68_avatar_big.jpg","lesson_id":"9509","from_type":1,"url":"http://edu.zhulong.com/lesson/9509-1.html"},{"live_id":"16580","teacher_name":"雷浩鹏","coverImgUrl":"https://newoss.zhulong.com/edu/202004/23/16/101116sguzegs8evz77nxc.jpg","activityName":"机电BIM工程师训练营第6-6次直播","correlative_lessons":"8266,9229,9206","startTime":"1591530641","endTime":"1591537800","teacher_uid":"11114735","specialty_id":"21","is_liveing":0,"teacher_photo":"https://avatar.zhulong.com/avatar/011/11/47/35_avatar_big.jpg","lesson_id":"9229","from_type":1,"url":"http://edu.zhulong.com/live/see_live?id=16580"}]}
     * upgrade : 0
     * up_msg :
     * exeTime : 0
     */

    private int errNo;
    private ResultBean result;
    private int upgrade;
    private String up_msg;
    private int exeTime;

    public int getErrNo() {
        return errNo;
    }

    public void setErrNo(int errNo) {
        this.errNo = errNo;
    }

    public ResultBean getResult() {
        return result;
    }

    public void setResult(ResultBean result) {
        this.result = result;
    }

    public int getUpgrade() {
        return upgrade;
    }

    public void setUpgrade(int upgrade) {
        this.upgrade = upgrade;
    }

    public String getUp_msg() {
        return up_msg;
    }

    public void setUp_msg(String up_msg) {
        this.up_msg = up_msg;
    }

    public int getExeTime() {
        return exeTime;
    }

    public void setExeTime(int exeTime) {
        this.exeTime = exeTime;
    }

    public static class ResultBean {
        private List<CarouselBean> Carousel;
        private List<LiveBean> live;

        public List<CarouselBean> getCarousel() {
            return Carousel;
        }

        public void setCarousel(List<CarouselBean> Carousel) {
            this.Carousel = Carousel;
        }

        public List<LiveBean> getLive() {
            return live;
        }

        public void setLive(List<LiveBean> live) {
            this.live = live;
        }

        public static class CarouselBean {
            /**
             * url : https://a.zhulong.com/poster/newjump/?plan_id=2913&prof=bim&placename_id=177&show_flag=1
             * img : https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710
             * thumb : https://f.zhulong.com/poster/202006/05/30/184330e75nhawq3r7xgpcw_300_600_550_310.jpg?t=20190710
             */

            private String url;
            private String img;
            private String thumb;

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }

            public String getImg() {
                return img;
            }

            public void setImg(String img) {
                this.img = img;
            }

            public String getThumb() {
                return thumb;
            }

            public void setThumb(String thumb) {
                this.thumb = thumb;
            }
        }

        public static class LiveBean {
            /**
             * live_id : 16972
             * teacher_name : 徐钢
             * coverImgUrl : https://newoss.zhulong.com/edu/202005/18/16/0957168tdjlfitcvq9vxyx.jpg
             * activityName : BIM考试二级结构直播答疑-第六次
             * correlative_lessons : 4971,9509
             * startTime : 1591530640
             * endTime : 1591535454
             * teacher_uid : 11442868
             * specialty_id : 21
             * is_liveing : 0
             * teacher_photo : https://avatar.zhulong.com/avatar/011/44/28/68_avatar_big.jpg
             * lesson_id : 9509
             * from_type : 1
             * url : http://edu.zhulong.com/lesson/9509-1.html
             */

            private String live_id;
            private String teacher_name;
            private String coverImgUrl;
            private String activityName;
            private String correlative_lessons;
            private String startTime;
            private String endTime;
            private String teacher_uid;
            private String specialty_id;
            private int is_liveing;
            private String teacher_photo;
            private String lesson_id;
            private int from_type;
            private String url;

            public String getLive_id() {
                return live_id;
            }

            public void setLive_id(String live_id) {
                this.live_id = live_id;
            }

            public String getTeacher_name() {
                return teacher_name;
            }

            public void setTeacher_name(String teacher_name) {
                this.teacher_name = teacher_name;
            }

            public String getCoverImgUrl() {
                return coverImgUrl;
            }

            public void setCoverImgUrl(String coverImgUrl) {
                this.coverImgUrl = coverImgUrl;
            }

            public String getActivityName() {
                return activityName;
            }

            public void setActivityName(String activityName) {
                this.activityName = activityName;
            }

            public String getCorrelative_lessons() {
                return correlative_lessons;
            }

            public void setCorrelative_lessons(String correlative_lessons) {
                this.correlative_lessons = correlative_lessons;
            }

            public String getStartTime() {
                return startTime;
            }

            public void setStartTime(String startTime) {
                this.startTime = startTime;
            }

            public String getEndTime() {
                return endTime;
            }

            public void setEndTime(String endTime) {
                this.endTime = endTime;
            }

            public String getTeacher_uid() {
                return teacher_uid;
            }

            public void setTeacher_uid(String teacher_uid) {
                this.teacher_uid = teacher_uid;
            }

            public String getSpecialty_id() {
                return specialty_id;
            }

            public void setSpecialty_id(String specialty_id) {
                this.specialty_id = specialty_id;
            }

            public int getIs_liveing() {
                return is_liveing;
            }

            public void setIs_liveing(int is_liveing) {
                this.is_liveing = is_liveing;
            }

            public String getTeacher_photo() {
                return teacher_photo;
            }

            public void setTeacher_photo(String teacher_photo) {
                this.teacher_photo = teacher_photo;
            }

            public String getLesson_id() {
                return lesson_id;
            }

            public void setLesson_id(String lesson_id) {
                this.lesson_id = lesson_id;
            }

            public int getFrom_type() {
                return from_type;
            }

            public void setFrom_type(int from_type) {
                this.from_type = from_type;
            }

            public String getUrl() {
                return url;
            }

            public void setUrl(String url) {
                this.url = url;
            }
        }
    }
}
