# M1

## New Feature

**Name:** [RANDOM ADAM MICKIEWICZ POEM BY WOLNE LEKTURY]

**Short description:** [THE IDEA: Every Polish high schoolers knows Wolne Lektury, a website where one can access for free most of their obligatory reading for the literature/philosophy class (this class is the trademark of Polish education system). Upon browsing the available APIs on github, I stumbled across the API of Wolne Lektury and could not resist calling it in my M1. 
So, the idea is to GET the poems of the most renowned Polish poet Adam Mickiewicz (some say he was Lithuanian btw - Adomas Mickevičius), pull them from the API when the app starts (it is 44 poems, so not too much, but the API request does take a few seconds), and display the author and title of a random one as a hyperlinked text leading to the poem on [Wolne Lektury](https://wolnelektury.pl/) every time the user enters main screen.
Creativity was requested, so here are the consequences.

THE CODE: To use the API, a retrofit client similar to the one used by the main functionality was created, with only one interface realising the GET of all poems by Adam Mickiewicz as per API docs. Since the author is always the same, the data format we need is just title and url, implemented as data class MickiewiczPoem. So overall, not much is stored in the RAM - some 88 strings. To be able to connect to network on start of the app, the API request has to be executed inside a runBlocking Kotlin block, as the calls are suspend functions. Then, the retrofit client object has an attribute containing the selected poem and a function that re-selects it, called on every creation of the Hypelinked Text Composable.]

**Location and code:** [frontend\app\src\main\java\com\cpen321\usermanagement\data\remote\wl

Next to that, required adding the hyper-linked text to MainScreen.kt and, pulling the poems from the Wolne Lektury API at MainActivity.kt]
