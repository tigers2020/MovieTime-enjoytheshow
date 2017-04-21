package com.androidnerdcolony.movietime_enjoytheshow.objects;

import java.util.List;

/**
 * Created by tiger on 4/21/2017.
 */

public class ConfigurationData {

    /**
     * images : {"base_url":"http://image.tmdb.org/t/p/","secure_base_url":"https://image.tmdb.org/t/p/","backdrop_sizes":["w300","w780","w1280","original"],"logo_sizes":["w45","w92","w154","w185","w300","w500","original"],"poster_sizes":["w92","w154","w185","w342","w500","w780","original"],"profile_sizes":["w45","w185","h632","original"],"still_sizes":["w92","w185","w300","original"]}
     * change_keys : ["adult","air_date","also_known_as","alternative_titles","biography","birthday","budget","cast","certifications","character_names","created_by","crew","deathday","episode","episode_number","episode_run_time","freebase_id","freebase_mid","general","genres","guest_stars","homepage","images","imdb_id","languages","name","network","origin_country","original_name","original_title","overview","parts","place_of_birth","plot_keywords","production_code","production_companies","production_countries","releases","revenue","runtime","season","season_number","season_regular","spoken_languages","status","tagline","title","translations","tvdb_id","tvrage_id","type","video","videos"]
     */

    private ImagesBean images;
    private List<String> change_keys;

    public ImagesBean getImages() {
        return images;
    }

    public void setImages(ImagesBean images) {
        this.images = images;
    }

    public List<String> getChange_keys() {
        return change_keys;
    }

    public void setChange_keys(List<String> change_keys) {
        this.change_keys = change_keys;
    }

    public static class ImagesBean {
        /**
         * base_url : http://image.tmdb.org/t/p/
         * secure_base_url : https://image.tmdb.org/t/p/
         * backdrop_sizes : ["w300","w780","w1280","original"]
         * logo_sizes : ["w45","w92","w154","w185","w300","w500","original"]
         * poster_sizes : ["w92","w154","w185","w342","w500","w780","original"]
         * profile_sizes : ["w45","w185","h632","original"]
         * still_sizes : ["w92","w185","w300","original"]
         */

        private String base_url;
        private String secure_base_url;
        private List<String> backdrop_sizes;
        private List<String> logo_sizes;
        private List<String> poster_sizes;
        private List<String> profile_sizes;
        private List<String> still_sizes;

        public String getBase_url() {
            return base_url;
        }

        public void setBase_url(String base_url) {
            this.base_url = base_url;
        }

        public String getSecure_base_url() {
            return secure_base_url;
        }

        public void setSecure_base_url(String secure_base_url) {
            this.secure_base_url = secure_base_url;
        }

        public List<String> getBackdrop_sizes() {
            return backdrop_sizes;
        }

        public void setBackdrop_sizes(List<String> backdrop_sizes) {
            this.backdrop_sizes = backdrop_sizes;
        }

        public List<String> getLogo_sizes() {
            return logo_sizes;
        }

        public void setLogo_sizes(List<String> logo_sizes) {
            this.logo_sizes = logo_sizes;
        }

        public List<String> getPoster_sizes() {
            return poster_sizes;
        }

        public void setPoster_sizes(List<String> poster_sizes) {
            this.poster_sizes = poster_sizes;
        }

        public List<String> getProfile_sizes() {
            return profile_sizes;
        }

        public void setProfile_sizes(List<String> profile_sizes) {
            this.profile_sizes = profile_sizes;
        }

        public List<String> getStill_sizes() {
            return still_sizes;
        }

        public void setStill_sizes(List<String> still_sizes) {
            this.still_sizes = still_sizes;
        }
    }
}
