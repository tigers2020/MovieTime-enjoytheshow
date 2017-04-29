package com.androidnerdcolony.movietime_enjoytheshow.objects;

import java.util.List;

/**
 * Created by tiger on 4/28/2017.
 */

public class MovieReleaseDateData {

    /**
     * id : 315837
     * results : [{"iso_3166_1":"AT","release_dates":[{"certification":"","iso_639_1":"de","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"IN","release_dates":[{"certification":"","iso_639_1":"en","release_date":"2017-04-07T00:00:00.000Z","type":3}]},{"iso_3166_1":"DK","release_dates":[{"certification":"11","iso_639_1":"","note":"","release_date":"2017-04-06T00:00:00.000Z","type":3}]},{"iso_3166_1":"CH","release_dates":[{"certification":"","iso_639_1":"","release_date":"2017-03-29T00:00:00.000Z","type":3}]},{"iso_3166_1":"JP","release_dates":[{"certification":"","iso_639_1":"ja","release_date":"2017-04-07T00:00:00.000Z","type":3}]},{"iso_3166_1":"GR","release_dates":[{"certification":"","iso_639_1":"","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"US","release_dates":[{"certification":"PG-13","iso_639_1":"","note":"","release_date":"2017-03-31T00:00:00.000Z","type":3}]},{"iso_3166_1":"IT","release_dates":[{"certification":"","iso_639_1":"it","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"DE","release_dates":[{"certification":"16","iso_639_1":"de","note":"","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"AU","release_dates":[{"certification":"M","iso_639_1":"en","note":"","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"ES","release_dates":[{"certification":"","iso_639_1":"","release_date":"2017-03-31T00:00:00.000Z","type":3}]},{"iso_3166_1":"BR","release_dates":[{"certification":"","iso_639_1":"pt","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"GB","release_dates":[{"certification":"12A","iso_639_1":"en","note":"","release_date":"2017-03-31T00:00:00.000Z","type":3}]},{"iso_3166_1":"UA","release_dates":[{"certification":"","iso_639_1":"uk","release_date":"2017-03-30T00:00:00.000Z","type":3}]},{"iso_3166_1":"FR","release_dates":[{"certification":"","iso_639_1":"fr","release_date":"2017-03-29T00:00:00.000Z","type":3}]},{"iso_3166_1":"RU","release_dates":[{"certification":"","iso_639_1":"","release_date":"2017-03-30T00:00:00.000Z","type":1},{"certification":"","iso_639_1":"ru","release_date":"2017-03-30T00:00:00.000Z","type":3}]}]
     */

    private int id;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * iso_3166_1 : AT
         * release_dates : [{"certification":"","iso_639_1":"de","release_date":"2017-03-30T00:00:00.000Z","type":3}]
         */

        private String iso_3166_1;
        private List<ReleaseDatesBean> release_dates;

        public String getIso_3166_1() {
            return iso_3166_1;
        }

        public void setIso_3166_1(String iso_3166_1) {
            this.iso_3166_1 = iso_3166_1;
        }

        public List<ReleaseDatesBean> getRelease_dates() {
            return release_dates;
        }

        public void setRelease_dates(List<ReleaseDatesBean> release_dates) {
            this.release_dates = release_dates;
        }

        public static class ReleaseDatesBean {
            /**
             * certification :
             * iso_639_1 : de
             * release_date : 2017-03-30T00:00:00.000Z
             * type : 3
             */

            private String certification;
            private String iso_639_1;
            private String release_date;
            private int type;

            public String getCertification() {
                return certification;
            }

            public void setCertification(String certification) {
                this.certification = certification;
            }

            public String getIso_639_1() {
                return iso_639_1;
            }

            public void setIso_639_1(String iso_639_1) {
                this.iso_639_1 = iso_639_1;
            }

            public String getRelease_date() {
                return release_date;
            }

            public void setRelease_date(String release_date) {
                this.release_date = release_date;
            }

            public int getType() {
                return type;
            }

            public void setType(int type) {
                this.type = type;
            }
        }
    }
}
