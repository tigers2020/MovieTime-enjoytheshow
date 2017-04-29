package com.androidnerdcolony.movietime_enjoytheshow.objects;

import java.util.List;

/**
 * Created by tiger on 4/28/2017.
 */

public class MovieImageData {

    /**
     * id : 315837
     * backdrops : [{"aspect_ratio":1.77777777777778,"file_path":"/yioNUiGVPyUjsoh4agW201YIw7m.jpg","height":720,"iso_639_1":"en","vote_average":0,"vote_count":0,"width":1280},{"aspect_ratio":1.77777777777778,"file_path":"/vjqsbs6vEwYNg98NSS8vgtbVAF0.jpg","height":720,"iso_639_1":"en","vote_average":0,"vote_count":0,"width":1280}]
     * posters : [{"aspect_ratio":0.666666666666667,"file_path":"/myRzRzCxdfUWjkJWgpHHZ1oGkJd.jpg","height":3000,"iso_639_1":"en","vote_average":5.89,"vote_count":21,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/si1ZyELNHdPUZw4pXR5KjMIIsBF.jpg","height":3000,"iso_639_1":"en","vote_average":5.684,"vote_count":17,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/xNaZhd6zcNVtC3eCQcKmcO2VwgQ.jpg","height":3000,"iso_639_1":"en","vote_average":5.52,"vote_count":8,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/nrLIFLCRwGjvuRbKn1fUjmU7X5A.jpg","height":3000,"iso_639_1":"en","vote_average":5.456,"vote_count":5,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/fAjZ4otBD3OQUGFrJ70YED6jRcU.jpg","height":3000,"iso_639_1":"en","vote_average":5.39,"vote_count":6,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/hScQGCaiTPfaZzZWLNSNve5Jzcx.jpg","height":3000,"iso_639_1":"en","vote_average":5.312,"vote_count":1,"width":2000},{"aspect_ratio":0.666666666666667,"file_path":"/uilFHP4nXYS8ZKKnfwxN7O9Tva.jpg","height":999,"iso_639_1":"en","vote_average":5.18,"vote_count":3,"width":666},{"aspect_ratio":0.666666666666667,"file_path":"/b40YfIM032Iut4psz5UPpYdP1LS.jpg","height":999,"iso_639_1":"en","vote_average":5.18,"vote_count":3,"width":666},{"aspect_ratio":0.675,"file_path":"/r0qI5p1iUVmaVots3wNoEt1bss4.jpg","height":960,"iso_639_1":"en","vote_average":5.172,"vote_count":1,"width":648},{"aspect_ratio":0.666666666666667,"file_path":"/2K5T6zj8p81r9pcjiM1iRNGjPKX.jpg","height":999,"iso_639_1":"en","vote_average":5.172,"vote_count":1,"width":666},{"aspect_ratio":0.666666666666667,"file_path":"/9vAcA1XUP2JxNYEuXqwcMorJ4T6.jpg","height":2160,"iso_639_1":"en","vote_average":5.128,"vote_count":6,"width":1440},{"aspect_ratio":0.666666666666667,"file_path":"/teu0jySRcDISHiZq1K9i4t0QnZS.jpg","height":999,"iso_639_1":"en","vote_average":5.044,"vote_count":3,"width":666},{"aspect_ratio":0.674388674388674,"file_path":"/9517sGtfdg3hNeOTaZCeUKK7zUg.jpg","height":1554,"iso_639_1":"en","vote_average":4.938,"vote_count":7,"width":1048}]
     */

    private int id;
    private List<BackdropsBean> backdrops;
    private List<PostersBean> posters;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<BackdropsBean> getBackdrops() {
        return backdrops;
    }

    public void setBackdrops(List<BackdropsBean> backdrops) {
        this.backdrops = backdrops;
    }

    public List<PostersBean> getPosters() {
        return posters;
    }

    public void setPosters(List<PostersBean> posters) {
        this.posters = posters;
    }

    public static class BackdropsBean {
        /**
         * aspect_ratio : 1.77777777777778
         * file_path : /yioNUiGVPyUjsoh4agW201YIw7m.jpg
         * height : 720
         * iso_639_1 : en
         * vote_average : 0.0
         * vote_count : 0
         * width : 1280
         */

        private double aspect_ratio;
        private String file_path;
        private int height;
        private String iso_639_1;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }

    public static class PostersBean {
        /**
         * aspect_ratio : 0.666666666666667
         * file_path : /myRzRzCxdfUWjkJWgpHHZ1oGkJd.jpg
         * height : 3000
         * iso_639_1 : en
         * vote_average : 5.89
         * vote_count : 21
         * width : 2000
         */

        private double aspect_ratio;
        private String file_path;
        private int height;
        private String iso_639_1;
        private double vote_average;
        private int vote_count;
        private int width;

        public double getAspect_ratio() {
            return aspect_ratio;
        }

        public void setAspect_ratio(double aspect_ratio) {
            this.aspect_ratio = aspect_ratio;
        }

        public String getFile_path() {
            return file_path;
        }

        public void setFile_path(String file_path) {
            this.file_path = file_path;
        }

        public int getHeight() {
            return height;
        }

        public void setHeight(int height) {
            this.height = height;
        }

        public String getIso_639_1() {
            return iso_639_1;
        }

        public void setIso_639_1(String iso_639_1) {
            this.iso_639_1 = iso_639_1;
        }

        public double getVote_average() {
            return vote_average;
        }

        public void setVote_average(double vote_average) {
            this.vote_average = vote_average;
        }

        public int getVote_count() {
            return vote_count;
        }

        public void setVote_count(int vote_count) {
            this.vote_count = vote_count;
        }

        public int getWidth() {
            return width;
        }

        public void setWidth(int width) {
            this.width = width;
        }
    }
}
