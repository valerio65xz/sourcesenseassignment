# Sourcesense  Assignment

A Simple Spring Boot project to aggregate news from HackerNews and New York Times.

# Requirements

Java > 1.8 is mandatory to run the project. If you want also to check and debug the code, you can use an IDE like IntelliJ
(recommended) or Eclipse.

# Installation 🛠️

You can clone the project from this link:

```sh
git clone https://github.com/valerio65xz/sourcesenseassignment.git
```

# Usage ℹ️

If you want to just execute the project, open a terminal in your installation folder and type:

```sh
java -jar SourcesenseAssignment.jar
```

then if you use Postman, you may import `SourcesenseAssignment.postman_collection.json` to import the collection.

There are 3 APIs:
* `GET news`: Get the aggregated news from both HackerNews and NewYorkTimes
* `GET news-from-hackernews`: Get the news only from HackerNews
* `GET news-from-newyorktimes`: Get the news only from NewYorkTimes

you have also to define a `limit` for HackerNews and a `section` for New York Times.

# Docs 📚

You can find the detailed documentation in `Documentazione.pdf` and the OpenAPI 3.0 Docs at `http://localhost:8090/swagger-ui/index.html`.
