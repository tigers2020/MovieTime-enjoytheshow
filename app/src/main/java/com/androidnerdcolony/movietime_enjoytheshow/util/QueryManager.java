package com.androidnerdcolony.movietime_enjoytheshow.util;

import android.content.Context;

import com.androidnerdcolony.movietime_enjoytheshow.R;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by tiger on 4/28/2017.
 */

public class QueryManager {

    private Map<String, String> query;

    public QueryManager(Map<String, String> query) {
        this.query = query;
    }

    private QueryManager(QueryBuilder builder) {
        this.query = builder.query;
    }

    public Map<String, String> getQuery() {
        return query;
    }

    public void setQuery(Map<String, String> query) {
        this.query = query;
    }

    public static class QueryBuilder {
        private Map<String, String> query = new HashMap<>();
        private Context context;

        QueryBuilder(Context context) {
            this.context = context;
        }

        QueryBuilder base() {
            this.query.put(context.getString(R.string.query_language), MoviePreferenceManager.getLanguage(context));
            this.query.put(context.getString(R.string.query_sort_by), MoviePreferenceManager.getSortBy(context));
            this.query.put(context.getString(R.string.query_region), MoviePreferenceManager.getRegion(context));
            this.query.put(context.getString(R.string.query_page), "1");
            return this;
        }

        QueryBuilder language() {
            this.query.put(context.getString(R.string.query_language), MoviePreferenceManager.getLanguage(context));
            return this;
        }

        QueryBuilder region() {
            this.query.put(context.getString(R.string.query_region), MoviePreferenceManager.getRegion(context));
            return this;
        }

        QueryBuilder sortBy(String sortBy) {
            this.query.put(context.getString(R.string.query_sort_by), sortBy);
            return this;
        }

        QueryBuilder certificationContry(String countryName) {
            this.query.put(context.getString(R.string.query_certification_country), countryName);
            return this;
        }

        QueryBuilder certification(String certification) {
            this.query.put(context.getString(R.string.query_certification), certification);
            return this;
        }

        QueryBuilder certificationLte(String certification_lte) {
            this.query.put(context.getString(R.string.query_certification_lte), certification_lte);
            return this;
        }

        QueryBuilder includeAdult() {
            this.query.put(context.getString(R.string.query_include_adult), MoviePreferenceManager.isIncludeAdult(context));
            return this;
        }

        QueryBuilder includeVideo() {
            this.query.put(context.getString(R.string.query_include_video), MoviePreferenceManager.isIncludeVideo(context));
            return this;
        }

        QueryBuilder page(String page) {
            this.query.put(context.getString(R.string.query_page), page);
            return this;
        }

        QueryBuilder primaryReleaseYear(String year) {
            this.query.put(context.getString(R.string.query_primary_release_year), year);
            return this;
        }

        QueryBuilder primaryReleaseDateGte(String date){
            this.query.put(context.getString(R.string.query_primary_release_date_gte), date);
            return this;
        }
        QueryBuilder primaryReleaseDateLte(String date){
            this.query.put(context.getString(R.string.query_primary_release_date_lte), date);
            return this;
        }
        QueryBuilder releaseDateGte(String date){
            this.query.put(context.getString(R.string.query_release_date_gte), date);
            return this;
        }
        QueryBuilder releaseDateLte(String date){
            this.query.put(context.getString(R.string.query_release_date_lte), date);
            return this;
        }
        QueryBuilder voteCountGte(String count){
            this.query.put(context.getString(R.string.query_vote_count_gte), count);
            return this;
        }
        QueryBuilder voteCountLte(String count){
            this.query.put(context.getString(R.string.query_vote_count_lte), count);
            return this;
        }
        QueryBuilder voteAverageGte(String count){
            this.query.put(context.getString(R.string.query_vote_average_gte), count);
            return this;
        }
        QueryBuilder voteAverageLte(String count){
            this.query.put(context.getString(R.string.query_vote_average_lte), count);
            return this;
        }
        QueryBuilder withCast(String name){
            this.query.put(context.getString(R.string.query_with_cast), name);
            return this;
        }
        QueryBuilder withCrew(String name){
            this.query.put(context.getString(R.string.query_with_crew), name);
            return this;
        }
        QueryBuilder withCompanies(String companies){
            this.query.put(context.getString(R.string.query_with_companies), companies);
            return this;
        }
        QueryBuilder withGenres(String genres){
            this.query.put(context.getString(R.string.query_with_genres), genres);
            return this;
        }
        QueryBuilder withKeywords(String keywords){
            this.query.put(context.getString(R.string.query_with_keywords), keywords);
            return this;
        }
        QueryBuilder withPeople(String people){
            this.query.put(context.getString(R.string.query_with_people), people);
            return this;
        }
        QueryBuilder year(String year){
            this.query.put(context.getString(R.string.query_year), year);
            return this;
        }
        QueryBuilder withoutGenres(String genres){
            this.query.put(context.getString(R.string.query_without_genres), genres);
            return this;
        }
        QueryBuilder withRunTimeGte(String runtime){
            this.query.put(context.getString(R.string.query_with_runtime_gte), runtime);
            return this;
        }
        QueryBuilder withRuntimeLte(String runtime){
            this.query.put(context.getString(R.string.query_with_runtime_lte), runtime);
            return this;
        }
        QueryBuilder withReleasetype(String type){
            this.query.put(context.getString(R.string.query_with_release_type), type);
            return this;
        }
        QueryBuilder withOriginalLanguage(String language){
            this.query.put(context.getString(R.string.query_with_original_language), language);
            return this;
        }
        QueryBuilder withoutKeywords(String keywords){
            this.query.put(context.getString(R.string.query_without_keywords), keywords);
            return this;
        }

        QueryBuilder airDateGte(String date){
            this.query.put(context.getString(R.string.query_air_date_gte), date);
            return this;
        }
        QueryBuilder airDateLte(String date){
            this.query.put(context.getString(R.string.query_air_date_lte), date);
            return this;
        }
        QueryBuilder firstAirDateGte(String date){
            this.query.put(context.getString(R.string.query_first_air_date_gte), date);
            return this;
        }
        QueryBuilder firstAirDateLte(String date){
            this.query.put(context.getString(R.string.query_first_air_date_lte), date);
            return this;
        }
        QueryBuilder timezone(String timezone){
            this.query.put(context.getString(R.string.query_timezone), timezone);
            return this;
        }
        QueryBuilder withNetworks(String networks){
            this.query.put(context.getString(R.string.query_with_network), networks);
            return this;
        }
        QueryBuilder includeNullFirstAirDates(String include)
        {
            this.query.put(context.getString(R.string.query_include_null_first_air_dates), include);
            return this;
        }

        QueryManager build(){
            return new QueryManager(this);
        }

    }
}
