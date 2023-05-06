Un semplice client mqtt che permette la sottoscrizione ad un broker.
Personalmente ho utilizzato questo sistema, per ottenere da un server mqtt posto su Raspberry gli eventi scatenati da alcuni sensori.

Lo sviluppo di questo piccolo client mi ha spinto all'utilizzo del BUILDER PATTERN (classe ClientBuilder). 
Metodologia di sviluppo che trovo davvero utile in quanto migliora la leggibilità del codice, e la validazione mediante unico metodo di build.

Da notare, che avendo untilizzato Lombok, sarebbe stato possibile creare la classe di Build con l'annotazione
@Builder(builderMethodName = "newBuilder") evitando cosi di scrivere i singoli metodi di set. 
