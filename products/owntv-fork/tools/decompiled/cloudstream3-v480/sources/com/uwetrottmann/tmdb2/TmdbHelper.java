package com.uwetrottmann.tmdb2;

import androidx.tvprovider.media.tv.TvContractCompat;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.uwetrottmann.tmdb2.entities.AccountStates;
import com.uwetrottmann.tmdb2.entities.BaseAccountStates;
import com.uwetrottmann.tmdb2.entities.BaseMovie;
import com.uwetrottmann.tmdb2.entities.BasePerson;
import com.uwetrottmann.tmdb2.entities.BaseTvShow;
import com.uwetrottmann.tmdb2.entities.Media;
import com.uwetrottmann.tmdb2.entities.PersonCastCredit;
import com.uwetrottmann.tmdb2.entities.PersonCrewCredit;
import com.uwetrottmann.tmdb2.entities.RatingObject;
import com.uwetrottmann.tmdb2.entities.Trending;
import com.uwetrottmann.tmdb2.enumerations.EpisodeType;
import com.uwetrottmann.tmdb2.enumerations.MediaType;
import com.uwetrottmann.tmdb2.enumerations.Status;
import com.uwetrottmann.tmdb2.enumerations.VideoType;
import java.lang.reflect.Type;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/* JADX INFO: loaded from: classes7.dex */
public class TmdbHelper {
    private static final ThreadLocal<SimpleDateFormat> TMDB_DATE_FORMAT = new ThreadLocal<>();
    public static final String TMDB_DATE_PATTERN = "yyyy-MM-dd";

    public static GsonBuilder getGsonBuilder() {
        GsonBuilder gsonBuilder = new GsonBuilder();
        gsonBuilder.registerTypeAdapter(Integer.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda0
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return Integer.valueOf(jsonElement.getAsInt());
            }
        });
        gsonBuilder.registerTypeAdapter(MediaType.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda5
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return MediaType.get(jsonElement.getAsString());
            }
        });
        gsonBuilder.registerTypeAdapter(VideoType.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda6
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return VideoType.get(jsonElement.getAsString());
            }
        });
        gsonBuilder.registerTypeAdapter(BaseAccountStates.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda7
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$3(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(AccountStates.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda8
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$4(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(Media.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda9
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$5(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(PersonCastCredit.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda10
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$6(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(PersonCrewCredit.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda11
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$7(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(Date.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda1
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$8(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(Status.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda2
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$9(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(Trending.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda3
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$10(jsonElement, type, jsonDeserializationContext);
            }
        });
        gsonBuilder.registerTypeAdapter(EpisodeType.class, new JsonDeserializer() { // from class: com.uwetrottmann.tmdb2.TmdbHelper$$ExternalSyntheticLambda4
            @Override // com.google.gson.JsonDeserializer
            public final Object deserialize(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) {
                return TmdbHelper.lambda$getGsonBuilder$11(jsonElement, type, jsonDeserializationContext);
            }
        });
        return gsonBuilder;
    }

    static /* synthetic */ BaseAccountStates lambda$getGsonBuilder$3(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        BaseAccountStates baseAccountStates = new BaseAccountStates();
        deserializeBaseAccountStates(jsonDeserializationContext, asJsonObject, baseAccountStates);
        return baseAccountStates;
    }

    static /* synthetic */ AccountStates lambda$getGsonBuilder$4(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        JsonObject asJsonObject = jsonElement.getAsJsonObject();
        AccountStates accountStates = new AccountStates();
        deserializeBaseAccountStates(jsonDeserializationContext, asJsonObject, accountStates);
        if (asJsonObject.get("favorite") != null) {
            accountStates.favorite = Boolean.valueOf(asJsonObject.get("favorite").getAsBoolean());
            accountStates.watchlist = Boolean.valueOf(asJsonObject.get("watchlist").getAsBoolean());
        }
        if (asJsonObject.get(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER) != null) {
            accountStates.episode_number = Integer.valueOf(asJsonObject.get(TvContractCompat.Programs.COLUMN_EPISODE_NUMBER).getAsInt());
        }
        return accountStates;
    }

    static /* synthetic */ Media lambda$getGsonBuilder$5(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Media media = new Media();
        if (jsonElement.getAsJsonObject().get("media_type") != null) {
            media.media_type = (MediaType) jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject().get("media_type"), MediaType.class);
        } else if (jsonElement.getAsJsonObject().get("first_air_date") != null) {
            media.media_type = MediaType.TV;
        } else if (jsonElement.getAsJsonObject().get("name") != null) {
            media.media_type = MediaType.PERSON;
        } else if (jsonElement.getAsJsonObject().get("title") != null) {
            media.media_type = MediaType.MOVIE;
        }
        int i = AnonymousClass1.$SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType[media.media_type.ordinal()];
        if (i == 1) {
            media.movie = (BaseMovie) jsonDeserializationContext.deserialize(jsonElement, BaseMovie.class);
            return media;
        }
        if (i == 2) {
            media.tvShow = (BaseTvShow) jsonDeserializationContext.deserialize(jsonElement, BaseTvShow.class);
            return media;
        }
        if (i != 3) {
            return media;
        }
        media.person = (BasePerson) jsonDeserializationContext.deserialize(jsonElement, BasePerson.class);
        return media;
    }

    static /* synthetic */ PersonCastCredit lambda$getGsonBuilder$6(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        PersonCastCredit personCastCredit = new PersonCastCredit();
        personCastCredit.media = (Media) jsonDeserializationContext.deserialize(jsonElement, Media.class);
        JsonElement jsonElement2 = jsonElement.getAsJsonObject().get("character");
        if (jsonElement2 != null) {
            personCastCredit.character = jsonElement2.getAsString();
        }
        JsonElement jsonElement3 = jsonElement.getAsJsonObject().get("credit_id");
        if (jsonElement3 != null) {
            personCastCredit.credit_id = jsonElement3.getAsString();
        }
        if (personCastCredit.media.media_type == MediaType.TV) {
            personCastCredit.episode_count = Integer.valueOf(jsonElement.getAsJsonObject().get("episode_count").getAsInt());
        }
        return personCastCredit;
    }

    static /* synthetic */ PersonCrewCredit lambda$getGsonBuilder$7(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        PersonCrewCredit personCrewCredit = new PersonCrewCredit();
        personCrewCredit.media = (Media) jsonDeserializationContext.deserialize(jsonElement, Media.class);
        personCrewCredit.department = jsonElement.getAsJsonObject().get("department").getAsString();
        personCrewCredit.job = jsonElement.getAsJsonObject().get("job").getAsString();
        personCrewCredit.credit_id = jsonElement.getAsJsonObject().get("credit_id").getAsString();
        if (personCrewCredit.media.media_type == MediaType.TV && jsonElement.getAsJsonObject().get("episode_count") != null) {
            personCrewCredit.episode_count = Integer.valueOf(jsonElement.getAsJsonObject().get("episode_count").getAsInt());
        }
        return personCrewCredit;
    }

    static /* synthetic */ Date lambda$getGsonBuilder$8(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        try {
            ThreadLocal<SimpleDateFormat> threadLocal = TMDB_DATE_FORMAT;
            SimpleDateFormat simpleDateFormat = threadLocal.get();
            if (simpleDateFormat == null) {
                simpleDateFormat = new SimpleDateFormat(TMDB_DATE_PATTERN);
                threadLocal.set(simpleDateFormat);
            }
            return simpleDateFormat.parse(jsonElement.getAsString());
        } catch (ParseException unused) {
            return null;
        }
    }

    static /* synthetic */ Status lambda$getGsonBuilder$9(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String asString = jsonElement.getAsString();
        if (asString != null) {
            return Status.fromValue(asString);
        }
        return null;
    }

    static /* synthetic */ Trending lambda$getGsonBuilder$10(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        Trending trending = new Trending();
        trending.media_type = (MediaType) jsonDeserializationContext.deserialize(jsonElement.getAsJsonObject().get("media_type"), MediaType.class);
        int i = AnonymousClass1.$SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType[trending.media_type.ordinal()];
        if (i == 1) {
            trending.movie = (BaseMovie) jsonDeserializationContext.deserialize(jsonElement, BaseMovie.class);
            return trending;
        }
        if (i == 2) {
            trending.tvShow = (BaseTvShow) jsonDeserializationContext.deserialize(jsonElement, BaseTvShow.class);
            return trending;
        }
        if (i != 3) {
            return trending;
        }
        trending.person = (BasePerson) jsonDeserializationContext.deserialize(jsonElement, BasePerson.class);
        return trending;
    }

    /* JADX INFO: renamed from: com.uwetrottmann.tmdb2.TmdbHelper$1, reason: invalid class name */
    static /* synthetic */ class AnonymousClass1 {
        static final /* synthetic */ int[] $SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType;

        static {
            int[] iArr = new int[MediaType.values().length];
            $SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType = iArr;
            try {
                iArr[MediaType.MOVIE.ordinal()] = 1;
            } catch (NoSuchFieldError unused) {
            }
            try {
                $SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType[MediaType.TV.ordinal()] = 2;
            } catch (NoSuchFieldError unused2) {
            }
            try {
                $SwitchMap$com$uwetrottmann$tmdb2$enumerations$MediaType[MediaType.PERSON.ordinal()] = 3;
            } catch (NoSuchFieldError unused3) {
            }
        }
    }

    static /* synthetic */ EpisodeType lambda$getGsonBuilder$11(JsonElement jsonElement, Type type, JsonDeserializationContext jsonDeserializationContext) throws JsonParseException {
        String asString = jsonElement.getAsString();
        if (asString != null) {
            return EpisodeType.fromValue(asString);
        }
        return null;
    }

    private static void deserializeBaseAccountStates(JsonDeserializationContext jsonDeserializationContext, JsonObject jsonObject, BaseAccountStates baseAccountStates) {
        baseAccountStates.id = Integer.valueOf(jsonObject.get("id").getAsInt());
        try {
            baseAccountStates.rated = Boolean.valueOf(jsonObject.get("rated").getAsBoolean());
        } catch (Exception unused) {
            baseAccountStates.rated = true;
            baseAccountStates.rating = (RatingObject) jsonDeserializationContext.deserialize(jsonObject.get("rated"), RatingObject.class);
        }
    }
}
