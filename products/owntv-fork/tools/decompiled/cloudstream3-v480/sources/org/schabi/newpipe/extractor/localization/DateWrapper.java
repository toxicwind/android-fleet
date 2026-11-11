package org.schabi.newpipe.extractor.localization;

import j$.time.Instant;
import j$.time.LocalDateTime;
import j$.time.OffsetDateTime;
import j$.time.ZoneId;
import j$.time.ZoneOffset;
import j$.time.format.DateTimeParseException;
import java.io.Serializable;
import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import org.schabi.newpipe.extractor.exceptions.ParsingException;

/* JADX INFO: loaded from: classes8.dex */
public class DateWrapper implements Serializable {

    @Nonnull
    private final Instant instant;
    private final boolean isApproximation;

    public DateWrapper(@Nonnull OffsetDateTime offsetDateTime) {
        this(offsetDateTime, false);
    }

    public DateWrapper(@Nonnull OffsetDateTime offsetDateTime, boolean z) {
        this(offsetDateTime.toInstant(), z);
    }

    public DateWrapper(@Nonnull Instant instant) {
        this(instant, false);
    }

    public DateWrapper(@Nonnull Instant instant, boolean z) {
        this.instant = instant;
        this.isApproximation = z;
    }

    public DateWrapper(@Nonnull LocalDateTime localDateTime, boolean z) {
        this(localDateTime.atZone(ZoneId.systemDefault()).toInstant(), z);
    }

    @Nonnull
    public Instant getInstant() {
        return this.instant;
    }

    @Nonnull
    public OffsetDateTime offsetDateTime() {
        return this.instant.atOffset(ZoneOffset.UTC);
    }

    @Nonnull
    public LocalDateTime getLocalDateTime() {
        return getLocalDateTime(ZoneId.systemDefault());
    }

    @Nonnull
    public LocalDateTime getLocalDateTime(@Nonnull ZoneId zoneId) {
        return LocalDateTime.ofInstant(this.instant, zoneId);
    }

    public boolean isApproximation() {
        return this.isApproximation;
    }

    public String toString() {
        return "DateWrapper{instant=" + this.instant + ", isApproximation=" + this.isApproximation + "}";
    }

    @Nullable
    public static DateWrapper fromOffsetDateTime(String str) throws ParsingException {
        if (str == null) {
            return null;
        }
        try {
            return new DateWrapper(OffsetDateTime.parse(str));
        } catch (DateTimeParseException e) {
            throw new ParsingException("Could not parse date: \"" + str + "\"", e);
        }
    }

    @Nullable
    public static DateWrapper fromInstant(String str) throws ParsingException {
        if (str == null) {
            return null;
        }
        try {
            return new DateWrapper(Instant.parse(str));
        } catch (DateTimeParseException e) {
            throw new ParsingException("Could not parse date: \"" + str + "\"", e);
        }
    }
}
