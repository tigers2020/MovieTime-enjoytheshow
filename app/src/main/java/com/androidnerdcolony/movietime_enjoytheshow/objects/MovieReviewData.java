package com.androidnerdcolony.movietime_enjoytheshow.objects;

import java.util.List;

/**
 * Created by tiger on 4/28/2017.
 */

public class MovieReviewData {

    /**
     * id : 315837
     * page : 1
     * results : [{"id":"58e044e8c3a3683d3e0110b2","author":"Salt-and-Limes","content":"I am writing this review as someone who hasn't seen the original anime. I have also been very critical of the whitewashing of this film. With that I aside, I went to see it with willingness to give it the benefit of the doubt. \"Ghost in the Shell\" is well...a beautiful shell. \r\n\r\nThe visuals were absolutely breath taking. The action scenes flowed so beautifully with special effects. But that's all the movie had to offer. Take away the spectacles and you have a basic run-of-the-mill action movie. \r\n\r\nThe acting was fine. But honestly, the leads didn't have anything to actually sink their teeth into. Scarjo, and everyone else, was serviceable. \r\n\r\nIf you're looking for some mindless, yet stunning entertainment, then go see it.","url":"https://www.themoviedb.org/review/58e044e8c3a3683d3e0110b2"},{"id":"58e78fe5c3a368730204ce99","author":"Wanderless","content":"I've watched Ghost in the Shell at the cinema 3 days ago, knowing\r\nbeforehand about the controversies that have surrounded this movie for\r\nthe last 6 months or so. Science fiction movies are probably my\r\nfavorite genre and I also enjoyed most of Scarlett's movies for the\r\npast 8-9 years so those two factors were a plus for me going in. \r\n\r\nRegarding the whitewashing business, I think its been blown way, WAY\r\nout of proportion by social justice warriors with nothing better to do\r\nthan drag media attention over whatever they're feeling insecure about\r\nthis month/year. For those of you who might be on the fence about\r\nwatching a Caucasian actress in the role that (supposedly) should've\r\nbeen reserved to an Asian actress, please consider this a NON-MATTER\r\nand watch it anyway. The character she is portraying is SUPPOSED to\r\nlook Caucasian/white. It was the same in the source material and even\r\nthe creator of that anime said so in interviews.\r\n\r\nNow, is the movie any good? In my opinion, yes. Yes it was. It wasn't\r\namazing but at the very least entertaining. There is a good amount of\r\npeople who score this a 1/10 because they are butt hurt that the story\r\nwas changed a lot. Please ignore them and see for yourself even if\r\nyou're a fan of the anime and are able to keep an open mind. I think\r\nmaybe I was able to judge it more objectively because I had zero\r\nknowledge about the story in the anime going in, but, if nothing else,\r\nthe movie actually made me want to watch those old ones to compare.\r\n\r\nThe acting - 7.5/10 - Since Scarlett Johansson is the only big name\r\nthat the movie is being marketed alongside, I'd say she did a good job.\r\nAt no point in the movie could I say she didn't belong there. She\r\nplayed the part of cyborg who had difficulty belonging in a human world\r\nvery well. The cast is diverse enough in my opinion, though some of\r\nthem get pretty little screen time.\r\n\r\nThe visuals - 9/10 - If there is one point most critics/viewers are in\r\nalignment concerning this movie, that point is definitely the visuals.\r\nThe movie both looks and feels spectacular, with the futuristic city\r\nlooking like a close-future mix of Blade Runner and TRON. The\r\ncombination of CGI and practical effects looks organic, the movie's\r\npowerful themes of excessive self-augmentation and technology almost\r\nrunning amok represented very well visually.\r\n\r\nSoundtrack - 8/10 - Sometimes pretty subdued, sometimes\r\nalmost-but-not-quite in your face, I found the soundtrack to vary\r\nbetween decent and very good in some moments. It didn't MAKE the movie\r\nbut it enhanced a good deal in my opinion.\r\n\r\nStory - 7/10 - Here is where the good points of the movie kinda start\r\nto run dry. A lot of other people would probably rate it a lot lower,\r\nwith 5 or 4's if they're at least trying to be objective. Yes, the\r\nstory is fairly predictable, and the fact that the movie is only around\r\n100 minutes long doesn't do it any favors either. Here is probably\r\nwhere most of the legitimate hate towards this movie stems from. The\r\ncreators adapted a story that had a lot more depth and philosophical\r\ninsight and turned it into a somewhat generic cyborg coming of age\r\nstory mixed with an evil corporation doing questionable things. The\r\nvillain is also very cookie-cutter and has almost zero depth. HOWEVER,\r\nI do think that concerning this film's particular themes and narrative,\r\na weak villain doesn't hurt it so much since its more about\r\nlosing/gaining your humanity through technology than any bad guy trying\r\nto shoot you.\r\n\r\nWriting/Dialogue - 6/10 - By far the movie's weakest aspect. In fact, I\r\nbelieve if some more meaningful dialogue and character interactions\r\nwere written into this film, it could've easily been 1 or 2 points\r\nhigher on anyone's scoring system. As it is, the dialogue is shallow\r\nand fairly run-of-the-mill for about half the movie's length. Some bits\r\nof good interactions are sprinkled here and there, and thankfully\r\nthat's enough to preserve the soul of the movie's central theme of\r\nhuman souls surviving in machine bodies, BUT not enough to give Ghost\r\nin the Shell the depth it should've inherited from the source material.\r\n\r\nOverall - I gave this movie 3.5/5 stars here, mostly because I\r\ncouldn't give it a 7.5/10 which felt more appropriate to me. My advice\r\nwould be to not listen to the whitewashing nonsense, because that's\r\nexactly what it is, nonsense. Also don't listen to the haters who rate\r\nthis movie a 1/10 or call it shit because those people should not be\r\nreviewing anything to begin with. It is a decent movie, with great\r\nvisuals and a theme that might get you thinking for a couple of days\r\nafter seeing it. The acting is decent, with an above average\r\nperformance from Scarlett and a good soundtrack that might hit the\r\nright spot on occasion. The only bad aspects, like I mentioned, are the\r\nfilm's rather short running time and weak dialogue/writing which hold\r\nit back from being truly great.","url":"https://www.themoviedb.org/review/58e78fe5c3a368730204ce99"}]
     * total_pages : 1
     * total_results : 2
     */

    private int id;
    private int page;
    private int total_pages;
    private int total_results;
    private List<ResultsBean> results;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getTotal_pages() {
        return total_pages;
    }

    public void setTotal_pages(int total_pages) {
        this.total_pages = total_pages;
    }

    public int getTotal_results() {
        return total_results;
    }

    public void setTotal_results(int total_results) {
        this.total_results = total_results;
    }

    public List<ResultsBean> getResults() {
        return results;
    }

    public void setResults(List<ResultsBean> results) {
        this.results = results;
    }

    public static class ResultsBean {
        /**
         * id : 58e044e8c3a3683d3e0110b2
         * author : Salt-and-Limes
         * content : I am writing this review as someone who hasn't seen the original anime. I have also been very critical of the whitewashing of this film. With that I aside, I went to see it with willingness to give it the benefit of the doubt. "Ghost in the Shell" is well...a beautiful shell.

         The visuals were absolutely breath taking. The action scenes flowed so beautifully with special effects. But that's all the movie had to offer. Take away the spectacles and you have a basic run-of-the-mill action movie.

         The acting was fine. But honestly, the leads didn't have anything to actually sink their teeth into. Scarjo, and everyone else, was serviceable.

         If you're looking for some mindless, yet stunning entertainment, then go see it.
         * url : https://www.themoviedb.org/review/58e044e8c3a3683d3e0110b2
         */

        private String id;
        private String author;
        private String content;
        private String url;

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }
    }
}
