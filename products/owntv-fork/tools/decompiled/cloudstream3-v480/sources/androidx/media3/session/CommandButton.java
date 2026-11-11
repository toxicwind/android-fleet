package androidx.media3.session;

import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.util.SparseIntArray;
import androidx.media3.common.MediaItem;
import androidx.media3.common.MediaMetadata;
import androidx.media3.common.Player;
import androidx.media3.common.Rating;
import androidx.media3.common.TrackSelectionParameters;
import androidx.media3.common.util.Util;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import com.google.common.primitives.ImmutableIntArray;
import com.google.errorprone.annotations.CheckReturnValue;
import j$.util.Objects;
import java.lang.annotation.Documented;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.util.List;
import org.checkerframework.checker.nullness.qual.RequiresNonNull;

/* JADX INFO: loaded from: classes4.dex */
public final class CommandButton {
    private static final String CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY = "androidx.media3.session.CUSTOM_COMMAND_PARAMETER";
    private static final String CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX = "androidx.media3.session.PLAYER_COMMAND_";
    private static final String CUSTOM_COMMAND_SESSION_COMMAND_PREFIX = "androidx.media3.session.SESSION_COMMAND_";
    public static final int ICON_ALBUM = 57369;
    public static final int ICON_ARTIST = 57370;
    public static final int ICON_BLOCK = 57675;
    public static final int ICON_BOOKMARK_FILLED = 1042534;
    public static final int ICON_BOOKMARK_UNFILLED = 59494;
    public static final int ICON_CHECK_CIRCLE_FILLED = 1042540;
    public static final int ICON_CHECK_CIRCLE_UNFILLED = 59500;
    public static final int ICON_CLOSED_CAPTIONS = 57372;
    public static final int ICON_CLOSED_CAPTIONS_OFF = 61916;
    public static final int ICON_FAST_FORWARD = 57375;
    public static final int ICON_FEED = 57573;
    public static final int ICON_FLAG_FILLED = 1040723;
    public static final int ICON_FLAG_UNFILLED = 57683;
    public static final int ICON_HEART_FILLED = 1042557;
    public static final int ICON_HEART_UNFILLED = 59517;
    public static final int ICON_MINUS = 57691;
    public static final int ICON_MINUS_CIRCLE_FILLED = 1040712;
    public static final int ICON_MINUS_CIRCLE_UNFILLED = 1040713;
    public static final int ICON_NEXT = 57412;
    public static final int ICON_PAUSE = 57396;
    public static final int ICON_PLAY = 57399;
    public static final int ICON_PLAYBACK_SPEED = 57448;
    public static final int ICON_PLAYBACK_SPEED_0_5 = 62690;
    public static final int ICON_PLAYBACK_SPEED_0_8 = 1045730;
    public static final int ICON_PLAYBACK_SPEED_1_0 = 61389;
    public static final int ICON_PLAYBACK_SPEED_1_2 = 62689;
    public static final int ICON_PLAYBACK_SPEED_1_5 = 62688;
    public static final int ICON_PLAYBACK_SPEED_1_8 = 1045728;
    public static final int ICON_PLAYBACK_SPEED_2_0 = 62699;
    public static final int ICON_PLAYLIST_ADD = 57403;
    public static final int ICON_PLAYLIST_REMOVE = 60288;
    public static final int ICON_PLUS = 57669;
    public static final int ICON_PLUS_CIRCLE_FILLED = 1040711;
    public static final int ICON_PLUS_CIRCLE_UNFILLED = 57671;
    public static final int ICON_PREVIOUS = 57413;
    public static final int ICON_QUALITY = 58409;
    public static final int ICON_QUEUE_ADD = 57436;
    public static final int ICON_QUEUE_NEXT = 57446;
    public static final int ICON_QUEUE_REMOVE = 57447;
    public static final int ICON_RADIO = 58654;
    public static final int ICON_REPEAT_ALL = 57408;
    public static final int ICON_REPEAT_OFF = 1040448;
    public static final int ICON_REPEAT_ONE = 57409;
    public static final int ICON_REWIND = 57376;
    public static final int ICON_SETTINGS = 59576;
    public static final int ICON_SHARE = 59405;
    public static final int ICON_SHUFFLE_OFF = 1040452;
    public static final int ICON_SHUFFLE_ON = 57411;
    public static final int ICON_SHUFFLE_STAR = 1040451;
    public static final int ICON_SIGNAL = 61512;
    public static final int ICON_SKIP_BACK = 57410;
    public static final int ICON_SKIP_BACK_10 = 57433;
    public static final int ICON_SKIP_BACK_15 = 1040473;
    public static final int ICON_SKIP_BACK_30 = 57434;
    public static final int ICON_SKIP_BACK_5 = 57435;
    public static final int ICON_SKIP_FORWARD = 63220;
    public static final int ICON_SKIP_FORWARD_10 = 57430;
    public static final int ICON_SKIP_FORWARD_15 = 1040470;
    public static final int ICON_SKIP_FORWARD_30 = 57431;
    public static final int ICON_SKIP_FORWARD_5 = 57432;
    public static final int ICON_STAR_FILLED = 1042488;
    public static final int ICON_STAR_UNFILLED = 59448;
    public static final int ICON_STOP = 57415;
    public static final int ICON_SUBTITLES = 57416;
    public static final int ICON_SUBTITLES_OFF = 61298;
    public static final int ICON_SYNC = 58919;
    public static final int ICON_THUMB_DOWN_FILLED = 1042651;
    public static final int ICON_THUMB_DOWN_UNFILLED = 59611;
    public static final int ICON_THUMB_UP_FILLED = 1042652;
    public static final int ICON_THUMB_UP_UNFILLED = 59612;
    public static final int ICON_UNDEFINED = 0;
    public static final int ICON_VOLUME_DOWN = 57421;
    public static final int ICON_VOLUME_OFF = 57423;
    public static final int ICON_VOLUME_UP = 57424;
    private static final String INCORRECT_PARAMETER_TYPE_MESSAGE = "Parameter has incorrect type.";
    private static final int PARAMETER_TYPE_BOOLEAN = 3;
    private static final int PARAMETER_TYPE_FLOAT = 4;
    private static final int PARAMETER_TYPE_INT = 2;
    private static final int PARAMETER_TYPE_LONG = 1;
    private static final int PARAMETER_TYPE_MEDIA_ITEM = 6;
    private static final int PARAMETER_TYPE_MEDIA_METADATA = 7;
    private static final int PARAMETER_TYPE_NULL = 0;
    private static final int PARAMETER_TYPE_RATING = 5;
    private static final int PARAMETER_TYPE_TRACK_SELECTION_PARAMETERS = 8;
    public static final int SLOT_BACK = 2;
    public static final int SLOT_BACK_SECONDARY = 4;
    public static final int SLOT_CENTRAL = 1;
    public static final int SLOT_FORWARD = 3;
    public static final int SLOT_FORWARD_SECONDARY = 5;
    public static final int SLOT_OVERFLOW = 6;
    public final CharSequence displayName;
    public final Bundle extras;
    public final int icon;
    public final int iconResId;
    public final Uri iconUri;
    public final boolean isEnabled;
    public final Object parameter;
    public final int playerCommand;
    public final SessionCommand sessionCommand;
    public final ImmutableIntArray slots;
    private static final String FIELD_SESSION_COMMAND = Util.intToStringMaxRadix(0);
    private static final String FIELD_PLAYER_COMMAND = Util.intToStringMaxRadix(1);
    private static final String FIELD_ICON_RES_ID = Util.intToStringMaxRadix(2);
    private static final String FIELD_DISPLAY_NAME = Util.intToStringMaxRadix(3);
    private static final String FIELD_EXTRAS = Util.intToStringMaxRadix(4);
    private static final String FIELD_ENABLED = Util.intToStringMaxRadix(5);
    private static final String FIELD_ICON_URI = Util.intToStringMaxRadix(6);
    private static final String FIELD_ICON = Util.intToStringMaxRadix(7);
    private static final String FIELD_SLOTS = Util.intToStringMaxRadix(8);
    private static final String FIELD_PARAMETER = Util.intToStringMaxRadix(9);

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Icon {
    }

    @Target({ElementType.TYPE_USE})
    @Documented
    @Retention(RetentionPolicy.SOURCE)
    public @interface Slot {
    }

    public static int getDefaultSlot(int i, int i2) {
        if (i == 1 || i2 == 57399 || i2 == 57396) {
            return 1;
        }
        if (i == 11 || i == 7 || i == 6 || i2 == 57413 || i2 == 57376 || i2 == 57410 || i2 == 57435 || i2 == 57433 || i2 == 1040473 || i2 == 57434) {
            return 2;
        }
        return (i == 12 || i == 9 || i == 8 || i2 == 57412 || i2 == 57375 || i2 == 63220 || i2 == 57432 || i2 == 57430 || i2 == 1040470 || i2 == 57431) ? 3 : 6;
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getParameterTypeForPlayerCommand(int i) {
        if (i == 1) {
            return 3;
        }
        if (i == 5) {
            return 1;
        }
        if (i == 10) {
            return 2;
        }
        if (i == 19) {
            return 7;
        }
        if (i == 24) {
            return 4;
        }
        if (i == 29) {
            return 8;
        }
        if (i == 31) {
            return 6;
        }
        switch (i) {
            case 13:
                return 4;
            case 14:
                return 3;
            case 15:
                return 2;
            default:
                return 0;
        }
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static int getParameterTypeForSessionCommand(int i) {
        return i == 40010 ? 5 : 0;
    }

    public static final class Builder {
        private CharSequence displayName;
        private boolean enabled;
        private Bundle extras;
        private final int icon;
        private int iconResId;
        private Uri iconUri;
        private Object parameter;
        private int playerCommand;
        private SessionCommand sessionCommand;
        private ImmutableIntArray slots;

        @Deprecated
        public Builder() {
            this(0);
        }

        public Builder(int i) {
            this(i, CommandButton.getIconResIdForIconConstant(i));
        }

        Builder(int i, int i2) {
            this.icon = i;
            this.iconResId = i2;
            this.displayName = "";
            this.extras = Bundle.EMPTY;
            this.playerCommand = -1;
            this.enabled = true;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand) {
            Preconditions.checkNotNull(sessionCommand, "sessionCommand should not be null.");
            Preconditions.checkArgument(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand;
            this.parameter = null;
            return this;
        }

        public Builder setSessionCommand(SessionCommand sessionCommand, Object obj) {
            Preconditions.checkNotNull(sessionCommand, "sessionCommand should not be null.");
            Preconditions.checkArgument(this.playerCommand == -1, "playerCommands is already set. Only one of sessionCommand and playerCommand should be set.");
            this.sessionCommand = sessionCommand;
            this.parameter = CommandButton.verifyParameterType(obj, CommandButton.getParameterTypeForSessionCommand(sessionCommand.commandCode));
            return this;
        }

        public Builder setPlayerCommand(int i) {
            Preconditions.checkArgument(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i;
            this.parameter = null;
            return this;
        }

        public Builder setPlayerCommand(int i, Object obj) {
            Preconditions.checkArgument(this.sessionCommand == null, "sessionCommand is already set. Only one of sessionCommand and playerCommand should be set.");
            this.playerCommand = i;
            this.parameter = CommandButton.verifyParameterType(obj, CommandButton.getParameterTypeForPlayerCommand(i));
            return this;
        }

        @Deprecated
        public Builder setIconResId(int i) {
            return setCustomIconResId(i);
        }

        public Builder setCustomIconResId(int i) {
            this.iconResId = i;
            return this;
        }

        public Builder setIconUri(Uri uri) {
            Preconditions.checkArgument(Objects.equals(uri.getScheme(), "content") || Objects.equals(uri.getScheme(), "android.resource"), "Only content or resource Uris are supported for CommandButton");
            this.iconUri = uri;
            return this;
        }

        public Builder setDisplayName(CharSequence charSequence) {
            this.displayName = charSequence;
            return this;
        }

        public Builder setEnabled(boolean z) {
            this.enabled = z;
            return this;
        }

        public Builder setExtras(Bundle bundle) {
            this.extras = new Bundle(bundle);
            return this;
        }

        public Builder setSlots(int... iArr) {
            Preconditions.checkArgument(iArr.length != 0);
            this.slots = ImmutableIntArray.copyOf(iArr);
            return this;
        }

        public CommandButton build() {
            Preconditions.checkState((this.sessionCommand == null) != (this.playerCommand == -1), "Exactly one of sessionCommand and playerCommand should be set");
            if (this.slots == null) {
                this.slots = ImmutableIntArray.of(CommandButton.getDefaultSlot(this.playerCommand, this.icon));
            }
            return new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.enabled, this.slots, this.parameter);
        }
    }

    public static final class DisplayConstraints {
        private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
        private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
        private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
        private final SparseIntArray maxButtonsPerSlot;

        public static final class Builder {
            private final SparseArray<Player.Commands> allowedPlayerCommandsPerSlot;
            private final SparseArray<SessionCommands> allowedSessionCommandsPerSlot;
            private final SparseBooleanArray areCustomCommandsAllowedPerSlot;
            private boolean buildCalled;
            private final SparseIntArray maxButtonsPerSlot;

            public Builder() {
                SparseIntArray sparseIntArray = new SparseIntArray();
                this.maxButtonsPerSlot = sparseIntArray;
                sparseIntArray.put(1, 1);
                sparseIntArray.put(2, 1);
                sparseIntArray.put(3, 1);
                sparseIntArray.put(6, Integer.MAX_VALUE);
                this.allowedPlayerCommandsPerSlot = new SparseArray<>();
                this.allowedSessionCommandsPerSlot = new SparseArray<>();
                this.areCustomCommandsAllowedPerSlot = new SparseBooleanArray();
            }

            public Builder setMaxButtonsForSlot(int i, int i2) {
                Preconditions.checkArgument(i2 >= 0);
                this.maxButtonsPerSlot.put(i, i2);
                return this;
            }

            public Builder setAllowedPlayerCommandsForSlot(int i, Player.Commands commands) {
                this.allowedPlayerCommandsPerSlot.put(i, commands);
                return this;
            }

            public Builder setAllowedSessionCommandsForSlot(int i, SessionCommands sessionCommands) {
                this.allowedSessionCommandsPerSlot.put(i, sessionCommands);
                return this;
            }

            public Builder setAllowCustomCommandsForSlot(int i, boolean z) {
                this.areCustomCommandsAllowedPerSlot.put(i, z);
                return this;
            }

            public DisplayConstraints build() {
                Preconditions.checkState(!this.buildCalled);
                this.buildCalled = true;
                return new DisplayConstraints(this);
            }
        }

        private DisplayConstraints(Builder builder) {
            this.maxButtonsPerSlot = builder.maxButtonsPerSlot;
            this.allowedPlayerCommandsPerSlot = builder.allowedPlayerCommandsPerSlot;
            this.allowedSessionCommandsPerSlot = builder.allowedSessionCommandsPerSlot;
            this.areCustomCommandsAllowedPerSlot = builder.areCustomCommandsAllowedPerSlot;
        }

        public ImmutableList<CommandButton> resolve(List<CommandButton> list, Player player) {
            SparseIntArray sparseIntArrayClone = this.maxButtonsPerSlot.clone();
            ImmutableList.Builder builder = ImmutableList.builder();
            CommandButton commandButton = null;
            CommandButton commandButton2 = null;
            int i = 0;
            while (true) {
                if (i >= list.size()) {
                    break;
                }
                CommandButton commandButton3 = list.get(i);
                for (int i2 = 0; i2 < commandButton3.slots.length(); i2++) {
                    int i3 = commandButton3.slots.get(i2);
                    if (reserveSlotForButton(commandButton3, i3, sparseIntArrayClone)) {
                        builder.add(commandButton3.copyWithSlots(ImmutableIntArray.of(i3)));
                        if (commandButton != null || i3 != 3) {
                            if (commandButton2 != null || i3 != 2) {
                                break;
                            }
                            commandButton2 = commandButton3;
                            break;
                        }
                        commandButton = commandButton3;
                        break;
                    }
                }
                i++;
            }
            Player.Commands availableCommands = player.getAvailableCommands();
            if (this.maxButtonsPerSlot.get(1) == sparseIntArrayClone.get(1)) {
                CommandButton commandButtonCreateButton = createButton(Util.shouldShowPlayButton(player) ? CommandButton.ICON_PLAY : CommandButton.ICON_PAUSE, 1, availableCommands);
                if (reserveSlotForButton(commandButtonCreateButton, 1, sparseIntArrayClone)) {
                    builder.add(commandButtonCreateButton);
                }
            }
            boolean z = commandButton2 == null && this.maxButtonsPerSlot.get(2) > 0;
            boolean z2 = commandButton == null && this.maxButtonsPerSlot.get(3) > 0;
            if (z && z2) {
                int firstAvailableOrFirstCommand = getFirstAvailableOrFirstCommand(availableCommands, 7, 9, 6, 8, 11, 12);
                CommandButton commandButtonCreateButton2 = createButton(getIconForPlayerCommand(firstAvailableOrFirstCommand, player), firstAvailableOrFirstCommand, availableCommands);
                int i4 = commandButtonCreateButton2.slots.get(0);
                if (reserveSlotForButton(commandButtonCreateButton2, i4, sparseIntArrayClone)) {
                    builder.add(commandButtonCreateButton2);
                }
                int i5 = i4 != 2 ? 2 : 3;
                CommandButton commandButtonCreateOppositeButton = createOppositeButton(commandButtonCreateButton2, i5, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton, i5, sparseIntArrayClone)) {
                    builder.add(commandButtonCreateOppositeButton);
                }
            } else if (z) {
                CommandButton commandButtonCreateOppositeButton2 = createOppositeButton(commandButton, 2, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton2, 2, sparseIntArrayClone)) {
                    builder.add(commandButtonCreateOppositeButton2);
                }
            } else if (z2) {
                CommandButton commandButtonCreateOppositeButton3 = createOppositeButton(commandButton2, 3, player);
                if (reserveSlotForButton(commandButtonCreateOppositeButton3, 3, sparseIntArrayClone)) {
                    builder.add(commandButtonCreateOppositeButton3);
                }
            }
            return builder.build();
        }

        /* JADX WARN: Code duplicated, block: B:11:0x0020  */
        private boolean reserveSlotForButton(CommandButton commandButton, int i, SparseIntArray sparseIntArray) {
            boolean z = false;
            if (sparseIntArray.get(i) == 0) {
                return false;
            }
            if (commandButton.playerCommand != -1) {
                Player.Commands commands = this.allowedPlayerCommandsPerSlot.get(i);
                if (commands == null || commands.contains(commandButton.playerCommand)) {
                    z = true;
                }
            } else if (((SessionCommand) Preconditions.checkNotNull(commandButton.sessionCommand)).commandCode == 0) {
                z = this.areCustomCommandsAllowedPerSlot.get(i, true);
            } else {
                SessionCommands sessionCommands = this.allowedSessionCommandsPerSlot.get(i);
                if (sessionCommands == null || sessionCommands.contains(commandButton.sessionCommand)) {
                    z = true;
                }
            }
            if (z) {
                sparseIntArray.put(i, sparseIntArray.get(i) - 1);
            }
            return z;
        }

        private static CommandButton createOppositeButton(CommandButton commandButton, int i, Player player) {
            Player.Commands availableCommands = player.getAvailableCommands();
            int oppositePlayerCommand = getOppositePlayerCommand(commandButton, i, availableCommands);
            int oppositeIcon = getOppositeIcon(commandButton);
            if (oppositeIcon == 0) {
                oppositeIcon = getIconForPlayerCommand(oppositePlayerCommand, player);
            }
            return createButton(oppositeIcon, oppositePlayerCommand, availableCommands);
        }

        private static CommandButton createButton(int i, int i2, Player.Commands commands) {
            return new Builder(i).setPlayerCommand(i2).setEnabled(commands.contains(i2)).build();
        }

        private static int getFirstAvailableOrFirstCommand(Player.Commands commands, int... iArr) {
            for (int i : iArr) {
                if (commands.contains(i)) {
                    return i;
                }
            }
            return iArr[0];
        }

        private static int getOppositePlayerCommand(CommandButton commandButton, int i, Player.Commands commands) {
            if (commandButton != null) {
                switch (commandButton.playerCommand) {
                    case 6:
                        return 8;
                    case 7:
                        return 9;
                    case 8:
                        return 6;
                    case 9:
                        return 7;
                    case 11:
                        return 12;
                    case 12:
                        return 11;
                }
            }
            if (i == 2) {
                return getFirstAvailableOrFirstCommand(commands, 7, 6, 11);
            }
            return getFirstAvailableOrFirstCommand(commands, 9, 8, 12);
        }

        private static int getOppositeIcon(CommandButton commandButton) {
            if (commandButton == null) {
                return 0;
            }
            switch (commandButton.icon) {
                case CommandButton.ICON_FAST_FORWARD /* 57375 */:
                    return CommandButton.ICON_REWIND;
                case CommandButton.ICON_REWIND /* 57376 */:
                    return CommandButton.ICON_FAST_FORWARD;
                case CommandButton.ICON_SKIP_BACK /* 57410 */:
                    return CommandButton.ICON_SKIP_FORWARD;
                case CommandButton.ICON_NEXT /* 57412 */:
                    return CommandButton.ICON_PREVIOUS;
                case CommandButton.ICON_PREVIOUS /* 57413 */:
                    return CommandButton.ICON_NEXT;
                case CommandButton.ICON_SKIP_FORWARD /* 63220 */:
                    return CommandButton.ICON_SKIP_BACK;
                default:
                    return 0;
            }
        }

        private static int getIconForPlayerCommand(int i, Player player) {
            switch (i) {
                case 6:
                case 7:
                    return CommandButton.ICON_PREVIOUS;
                case 8:
                case 9:
                    return CommandButton.ICON_NEXT;
                case 10:
                default:
                    throw new UnsupportedOperationException();
                case 11:
                    long seekBackIncrement = player.getSeekBackIncrement();
                    if (seekBackIncrement >= 2500 && seekBackIncrement < 7500) {
                        return CommandButton.ICON_SKIP_BACK_5;
                    }
                    if (seekBackIncrement >= 7500 && seekBackIncrement < 12500) {
                        return CommandButton.ICON_SKIP_BACK_10;
                    }
                    if (seekBackIncrement < 12500 || seekBackIncrement >= 20000) {
                        return (seekBackIncrement < 20000 || seekBackIncrement >= 40000) ? CommandButton.ICON_SKIP_BACK : CommandButton.ICON_SKIP_BACK_30;
                    }
                    return CommandButton.ICON_SKIP_BACK_15;
                case 12:
                    long seekForwardIncrement = player.getSeekForwardIncrement();
                    if (seekForwardIncrement >= 2500 && seekForwardIncrement < 7500) {
                        return CommandButton.ICON_SKIP_FORWARD_5;
                    }
                    if (seekForwardIncrement >= 7500 && seekForwardIncrement < 12500) {
                        return CommandButton.ICON_SKIP_FORWARD_10;
                    }
                    if (seekForwardIncrement < 12500 || seekForwardIncrement >= 20000) {
                        return (seekForwardIncrement < 20000 || seekForwardIncrement >= 40000) ? CommandButton.ICON_SKIP_FORWARD : CommandButton.ICON_SKIP_FORWARD_30;
                    }
                    return CommandButton.ICON_SKIP_FORWARD_15;
            }
        }
    }

    private CommandButton(SessionCommand sessionCommand, int i, int i2, int i3, Uri uri, CharSequence charSequence, Bundle bundle, boolean z, ImmutableIntArray immutableIntArray, Object obj) {
        this.sessionCommand = sessionCommand;
        this.playerCommand = i;
        this.icon = i2;
        this.iconResId = i3;
        this.iconUri = uri;
        this.displayName = charSequence;
        this.extras = new Bundle(bundle);
        this.isEnabled = z;
        this.slots = immutableIntArray;
        this.parameter = obj;
    }

    public void executeAction(MediaController mediaController) {
        Object obj;
        if (this.isEnabled) {
            SessionCommand sessionCommand = this.sessionCommand;
            if (sessionCommand != null) {
                int i = sessionCommand.commandCode;
                if (i == 0) {
                    mediaController.sendCustomCommand((SessionCommand) Preconditions.checkNotNull(this.sessionCommand), this.extras);
                    return;
                } else {
                    if (i == 40010 && (obj = this.parameter) != null) {
                        mediaController.setRating((Rating) obj);
                        return;
                    }
                    return;
                }
            }
            executePlayerAction(mediaController);
        }
    }

    void executePlayerAction(Player player) {
        if (this.isEnabled) {
            int i = this.playerCommand;
            if (i == 19) {
                Object obj = this.parameter;
                if (obj != null) {
                    player.setPlaylistMetadata((MediaMetadata) obj);
                    return;
                }
                return;
            }
            if (i == 24) {
                Object obj2 = this.parameter;
                if (obj2 != null) {
                    player.setVolume(((Float) obj2).floatValue());
                    return;
                } else if (player.getVolume() == 0.0f) {
                    player.unmute();
                    return;
                } else {
                    player.mute();
                    return;
                }
            }
            if (i == 29) {
                Object obj3 = this.parameter;
                if (obj3 != null) {
                    player.setTrackSelectionParameters((TrackSelectionParameters) obj3);
                    return;
                }
                return;
            }
            if (i != 31) {
                switch (i) {
                    case 1:
                        Object obj4 = this.parameter;
                        if (obj4 != null) {
                            player.setPlayWhenReady(((Boolean) obj4).booleanValue());
                        } else {
                            player.setPlayWhenReady(!player.getPlayWhenReady());
                        }
                        break;
                    case 2:
                        player.prepare();
                        break;
                    case 3:
                        player.stop();
                        break;
                    case 4:
                        player.seekToDefaultPosition();
                        break;
                    case 5:
                        Object obj5 = this.parameter;
                        if (obj5 != null) {
                            player.seekTo(((Long) obj5).longValue());
                        }
                        break;
                    case 6:
                        player.seekToPreviousMediaItem();
                        break;
                    case 7:
                        player.seekToPrevious();
                        break;
                    case 8:
                        player.seekToNextMediaItem();
                        break;
                    case 9:
                        player.seekToNext();
                        break;
                    case 10:
                        Object obj6 = this.parameter;
                        if (obj6 != null) {
                            player.seekToDefaultPosition(((Integer) obj6).intValue());
                        }
                        break;
                    case 11:
                        player.seekBack();
                        break;
                    case 12:
                        player.seekForward();
                        break;
                    case 13:
                        Object obj7 = this.parameter;
                        if (obj7 != null) {
                            player.setPlaybackSpeed(((Float) obj7).floatValue());
                        }
                        break;
                    case 14:
                        Object obj8 = this.parameter;
                        if (obj8 != null) {
                            player.setShuffleModeEnabled(((Boolean) obj8).booleanValue());
                        } else {
                            player.setShuffleModeEnabled(!player.getShuffleModeEnabled());
                        }
                        break;
                    case 15:
                        Object obj9 = this.parameter;
                        if (obj9 != null) {
                            player.setRepeatMode(((Integer) obj9).intValue());
                        }
                        break;
                }
                return;
            }
            Object obj10 = this.parameter;
            if (obj10 != null) {
                player.setMediaItem((MediaItem) obj10);
            }
        }
    }

    boolean isPlayRequestPlayerAction(Player player) {
        if (this.playerCommand != 1) {
            return false;
        }
        Object obj = this.parameter;
        if (obj == null) {
            return !player.getPlayWhenReady();
        }
        return ((Boolean) obj).booleanValue();
    }

    public boolean canExecuteAction() {
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            int i = sessionCommand.commandCode;
            if (i != 0) {
                return i == 40010 && this.parameter != null;
            }
            return true;
        }
        int i2 = this.playerCommand;
        if (i2 != 19) {
            if (i2 != 24) {
                if (i2 != 29 && i2 != 31) {
                    switch (i2) {
                        case 1:
                        case 2:
                        case 3:
                        case 4:
                        case 6:
                        case 7:
                        case 8:
                        case 9:
                        case 11:
                        case 12:
                        case 14:
                            break;
                        case 5:
                        case 10:
                        case 13:
                        case 15:
                            break;
                        default:
                            return false;
                    }
                }
            }
            return true;
        }
        return this.parameter != null;
    }

    @CheckReturnValue
    CommandButton copyWithIsEnabled(boolean z) {
        return this.isEnabled == z ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), z, this.slots, this.parameter);
    }

    @CheckReturnValue
    CommandButton copyWithSlots(ImmutableIntArray immutableIntArray) {
        return this.slots.equals(immutableIntArray) ? this : new CommandButton(this.sessionCommand, this.playerCommand, this.icon, this.iconResId, this.iconUri, this.displayName, new Bundle(this.extras), this.isEnabled, immutableIntArray, this.parameter);
    }

    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (!(obj instanceof CommandButton)) {
            return false;
        }
        CommandButton commandButton = (CommandButton) obj;
        return Objects.equals(this.sessionCommand, commandButton.sessionCommand) && this.playerCommand == commandButton.playerCommand && this.icon == commandButton.icon && this.iconResId == commandButton.iconResId && Objects.equals(this.iconUri, commandButton.iconUri) && TextUtils.equals(this.displayName, commandButton.displayName) && this.isEnabled == commandButton.isEnabled && this.slots.equals(commandButton.slots) && Objects.equals(this.parameter, commandButton.parameter);
    }

    public int hashCode() {
        return Objects.hash(this.sessionCommand, Integer.valueOf(this.playerCommand), Integer.valueOf(this.icon), Integer.valueOf(this.iconResId), this.displayName, Boolean.valueOf(this.isEnabled), this.iconUri, this.slots, this.parameter);
    }

    private CommandButton convertToPredefinedCustomCommandButton(int i) {
        String str;
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null && sessionCommand.commandCode == 0) {
            return copyWithSlots(ImmutableIntArray.of(i));
        }
        Bundle bundle = Bundle.EMPTY;
        if (this.parameter != null) {
            bundle = new Bundle();
            writeParameterToBundle(bundle, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY);
        }
        if (this.sessionCommand != null) {
            str = CUSTOM_COMMAND_SESSION_COMMAND_PREFIX + this.sessionCommand.commandCode;
        } else {
            str = CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX + this.playerCommand;
        }
        return new CommandButton(new SessionCommand(str, bundle), -1, this.icon, this.iconResId, this.iconUri, this.displayName, this.extras, this.isEnabled, ImmutableIntArray.of(i), null);
    }

    static ImmutableList<CommandButton> copyWithUnavailableButtonsDisabled(List<CommandButton> list, SessionCommands sessionCommands, Player.Commands commands) {
        ImmutableList.Builder builder = new ImmutableList.Builder();
        for (int i = 0; i < list.size(); i++) {
            CommandButton commandButton = list.get(i);
            if (isButtonCommandAvailable(commandButton, sessionCommands, commands)) {
                builder.add(commandButton);
            } else {
                builder.add(commandButton.copyWithIsEnabled(false));
            }
        }
        return builder.build();
    }

    static boolean isButtonCommandAvailable(CommandButton commandButton, SessionCommands sessionCommands, Player.Commands commands) {
        SessionCommand sessionCommand = commandButton.sessionCommand;
        if (sessionCommand != null && sessionCommands.contains(sessionCommand)) {
            return true;
        }
        int i = commandButton.playerCommand;
        return i != -1 && commands.contains(i);
    }

    public Bundle toBundle() {
        Bundle bundle = new Bundle();
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            bundle.putBundle(FIELD_SESSION_COMMAND, sessionCommand.toBundle());
        }
        int i = this.playerCommand;
        if (i != -1) {
            bundle.putInt(FIELD_PLAYER_COMMAND, i);
        }
        int i2 = this.icon;
        if (i2 != 0) {
            bundle.putInt(FIELD_ICON, i2);
        }
        int i3 = this.iconResId;
        if (i3 != 0) {
            bundle.putInt(FIELD_ICON_RES_ID, i3);
        }
        CharSequence charSequence = this.displayName;
        if (charSequence != "") {
            bundle.putCharSequence(FIELD_DISPLAY_NAME, charSequence);
        }
        if (!this.extras.isEmpty()) {
            bundle.putBundle(FIELD_EXTRAS, this.extras);
        }
        Uri uri = this.iconUri;
        if (uri != null) {
            bundle.putParcelable(FIELD_ICON_URI, uri);
        }
        boolean z = this.isEnabled;
        if (!z) {
            bundle.putBoolean(FIELD_ENABLED, z);
        }
        if (this.slots.length() != 1 || this.slots.get(0) != 6) {
            bundle.putIntArray(FIELD_SLOTS, this.slots.toArray());
        }
        if (this.parameter != null) {
            writeParameterToBundle(bundle, FIELD_PARAMETER);
        }
        return bundle;
    }

    @Deprecated
    public static CommandButton fromBundle(Bundle bundle) {
        return fromBundle(bundle, 8);
    }

    public static CommandButton fromBundle(Bundle bundle, int i) {
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMAND);
        SessionCommand sessionCommandFromBundle = bundle2 == null ? null : SessionCommand.fromBundle(bundle2);
        int i2 = bundle.getInt(FIELD_PLAYER_COMMAND, -1);
        int i3 = bundle.getInt(FIELD_ICON_RES_ID, 0);
        CharSequence charSequence = bundle.getCharSequence(FIELD_DISPLAY_NAME, "");
        Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle.getBundle(FIELD_EXTRAS));
        boolean z = i < 3 || bundle.getBoolean(FIELD_ENABLED, true);
        Uri uri = (Uri) bundle.getParcelable(FIELD_ICON_URI);
        int i4 = bundle.getInt(FIELD_ICON, 0);
        int[] intArray = bundle.getIntArray(FIELD_SLOTS);
        Builder builder = new Builder(i4, i3);
        if (sessionCommandFromBundle != null) {
            builder.setSessionCommand(sessionCommandFromBundle, getParameterFromBundle(bundle, FIELD_PARAMETER, getParameterTypeForSessionCommand(sessionCommandFromBundle.commandCode)));
        }
        if (i2 != -1) {
            builder.setPlayerCommand(i2, getParameterFromBundle(bundle, FIELD_PARAMETER, getParameterTypeForPlayerCommand(i2)));
        }
        if (uri != null && (Objects.equals(uri.getScheme(), "content") || Objects.equals(uri.getScheme(), "android.resource"))) {
            builder.setIconUri(uri);
        }
        Builder displayName = builder.setDisplayName(charSequence);
        if (bundleConvertToNullIfInvalid == null) {
            bundleConvertToNullIfInvalid = Bundle.EMPTY;
        }
        Builder enabled = displayName.setExtras(bundleConvertToNullIfInvalid).setEnabled(z);
        if (intArray == null) {
            intArray = new int[]{6};
        }
        return enabled.setSlots(intArray).build();
    }

    public static int getIconResIdForIconConstant(int i) {
        switch (i) {
            case ICON_ALBUM /* 57369 */:
                return R.drawable.media3_icon_album;
            case ICON_ARTIST /* 57370 */:
                return R.drawable.media3_icon_artist;
            case ICON_CLOSED_CAPTIONS /* 57372 */:
                return R.drawable.media3_icon_closed_captions;
            case ICON_FAST_FORWARD /* 57375 */:
                return R.drawable.media3_icon_fast_forward;
            case ICON_REWIND /* 57376 */:
                return R.drawable.media3_icon_rewind;
            case ICON_PAUSE /* 57396 */:
                return R.drawable.media3_icon_pause;
            case ICON_PLAY /* 57399 */:
                return R.drawable.media3_icon_play;
            case ICON_PLAYLIST_ADD /* 57403 */:
                return R.drawable.media3_icon_playlist_add;
            case ICON_REPEAT_ALL /* 57408 */:
                return R.drawable.media3_icon_repeat_all;
            case ICON_REPEAT_ONE /* 57409 */:
                return R.drawable.media3_icon_repeat_one;
            case ICON_SKIP_BACK /* 57410 */:
                return R.drawable.media3_icon_skip_back;
            case ICON_SHUFFLE_ON /* 57411 */:
                return R.drawable.media3_icon_shuffle_on;
            case ICON_NEXT /* 57412 */:
                return R.drawable.media3_icon_next;
            case ICON_PREVIOUS /* 57413 */:
                return R.drawable.media3_icon_previous;
            case ICON_STOP /* 57415 */:
                return R.drawable.media3_icon_stop;
            case ICON_SUBTITLES /* 57416 */:
                return R.drawable.media3_icon_subtitles;
            case ICON_VOLUME_DOWN /* 57421 */:
                return R.drawable.media3_icon_volume_down;
            case ICON_VOLUME_OFF /* 57423 */:
                return R.drawable.media3_icon_volume_off;
            case ICON_VOLUME_UP /* 57424 */:
                return R.drawable.media3_icon_volume_up;
            case ICON_SKIP_FORWARD_10 /* 57430 */:
                return R.drawable.media3_icon_skip_forward_10;
            case ICON_SKIP_FORWARD_30 /* 57431 */:
                return R.drawable.media3_icon_skip_forward_30;
            case ICON_SKIP_FORWARD_5 /* 57432 */:
                return R.drawable.media3_icon_skip_forward_5;
            case ICON_SKIP_BACK_10 /* 57433 */:
                return R.drawable.media3_icon_skip_back_10;
            case ICON_SKIP_BACK_30 /* 57434 */:
                return R.drawable.media3_icon_skip_back_30;
            case ICON_SKIP_BACK_5 /* 57435 */:
                return R.drawable.media3_icon_skip_back_5;
            case ICON_QUEUE_ADD /* 57436 */:
                return R.drawable.media3_icon_queue_add;
            case ICON_QUEUE_NEXT /* 57446 */:
                return R.drawable.media3_icon_queue_next;
            case ICON_QUEUE_REMOVE /* 57447 */:
                return R.drawable.media3_icon_queue_remove;
            case ICON_PLAYBACK_SPEED /* 57448 */:
                return R.drawable.media3_icon_playback_speed;
            case ICON_FEED /* 57573 */:
                return R.drawable.media3_icon_feed;
            case ICON_PLUS /* 57669 */:
                return R.drawable.media3_icon_plus;
            case ICON_PLUS_CIRCLE_UNFILLED /* 57671 */:
                return R.drawable.media3_icon_plus_circle_unfilled;
            case ICON_BLOCK /* 57675 */:
                return R.drawable.media3_icon_block;
            case ICON_FLAG_UNFILLED /* 57683 */:
                return R.drawable.media3_icon_flag_unfilled;
            case ICON_MINUS /* 57691 */:
                return R.drawable.media3_icon_minus;
            case ICON_QUALITY /* 58409 */:
                return R.drawable.media3_icon_quality;
            case ICON_RADIO /* 58654 */:
                return R.drawable.media3_icon_radio;
            case ICON_SYNC /* 58919 */:
                return R.drawable.media3_icon_sync;
            case ICON_SHARE /* 59405 */:
                return R.drawable.media3_icon_share;
            case ICON_STAR_UNFILLED /* 59448 */:
                return R.drawable.media3_icon_star_unfilled;
            case ICON_BOOKMARK_UNFILLED /* 59494 */:
                return R.drawable.media3_icon_bookmark_unfilled;
            case ICON_CHECK_CIRCLE_UNFILLED /* 59500 */:
                return R.drawable.media3_icon_check_circle_unfilled;
            case ICON_HEART_UNFILLED /* 59517 */:
                return R.drawable.media3_icon_heart_unfilled;
            case ICON_SETTINGS /* 59576 */:
                return R.drawable.media3_icon_settings;
            case ICON_THUMB_DOWN_UNFILLED /* 59611 */:
                return R.drawable.media3_icon_thumb_down_unfilled;
            case ICON_THUMB_UP_UNFILLED /* 59612 */:
                return R.drawable.media3_icon_thumb_up_unfilled;
            case ICON_PLAYLIST_REMOVE /* 60288 */:
                return R.drawable.media3_icon_playlist_remove;
            case ICON_SUBTITLES_OFF /* 61298 */:
                return R.drawable.media3_icon_subtitles_off;
            case ICON_PLAYBACK_SPEED_1_0 /* 61389 */:
                return R.drawable.media3_icon_playback_speed_1_0;
            case ICON_SIGNAL /* 61512 */:
                return R.drawable.media3_icon_signal;
            case ICON_CLOSED_CAPTIONS_OFF /* 61916 */:
                return R.drawable.media3_icon_closed_captions_off;
            case ICON_PLAYBACK_SPEED_1_5 /* 62688 */:
                return R.drawable.media3_icon_playback_speed_1_5;
            case ICON_PLAYBACK_SPEED_1_2 /* 62689 */:
                return R.drawable.media3_icon_playback_speed_1_2;
            case ICON_PLAYBACK_SPEED_0_5 /* 62690 */:
                return R.drawable.media3_icon_playback_speed_0_5;
            case ICON_PLAYBACK_SPEED_2_0 /* 62699 */:
                return R.drawable.media3_icon_playback_speed_2_0;
            case ICON_SKIP_FORWARD /* 63220 */:
                return R.drawable.media3_icon_skip_forward;
            case ICON_REPEAT_OFF /* 1040448 */:
                return R.drawable.media3_icon_repeat_off;
            case ICON_SHUFFLE_STAR /* 1040451 */:
                return R.drawable.media3_icon_shuffle_star;
            case ICON_SHUFFLE_OFF /* 1040452 */:
                return R.drawable.media3_icon_shuffle_off;
            case ICON_SKIP_FORWARD_15 /* 1040470 */:
                return R.drawable.media3_icon_skip_forward_15;
            case ICON_SKIP_BACK_15 /* 1040473 */:
                return R.drawable.media3_icon_skip_back_15;
            case ICON_PLUS_CIRCLE_FILLED /* 1040711 */:
                return R.drawable.media3_icon_plus_circle_filled;
            case ICON_MINUS_CIRCLE_FILLED /* 1040712 */:
                return R.drawable.media3_icon_minus_circle_filled;
            case ICON_MINUS_CIRCLE_UNFILLED /* 1040713 */:
                return R.drawable.media3_icon_minus_circle_unfilled;
            case ICON_FLAG_FILLED /* 1040723 */:
                return R.drawable.media3_icon_flag_filled;
            case ICON_STAR_FILLED /* 1042488 */:
                return R.drawable.media3_icon_star_filled;
            case ICON_BOOKMARK_FILLED /* 1042534 */:
                return R.drawable.media3_icon_bookmark_filled;
            case ICON_CHECK_CIRCLE_FILLED /* 1042540 */:
                return R.drawable.media3_icon_check_circle_filled;
            case ICON_HEART_FILLED /* 1042557 */:
                return R.drawable.media3_icon_heart_filled;
            case ICON_THUMB_DOWN_FILLED /* 1042651 */:
                return R.drawable.media3_icon_thumb_down_filled;
            case ICON_THUMB_UP_FILLED /* 1042652 */:
                return R.drawable.media3_icon_thumb_up_filled;
            case ICON_PLAYBACK_SPEED_1_8 /* 1045728 */:
                return R.drawable.media3_icon_playback_speed_1_8;
            case ICON_PLAYBACK_SPEED_0_8 /* 1045730 */:
                return R.drawable.media3_icon_playback_speed_0_8;
            default:
                return 0;
        }
    }

    static ImmutableList<CommandButton> getCustomLayoutFromMediaButtonPreferences(List<CommandButton> list, boolean z, boolean z2) {
        int i;
        if (list.isEmpty()) {
            return ImmutableList.of();
        }
        int i2 = -1;
        int i3 = -1;
        for (int i4 = 0; i4 < list.size(); i4++) {
            CommandButton commandButton = list.get(i4);
            if (commandButton.isEnabled && commandButton.canExecuteAction()) {
                for (int i5 = 0; i5 < commandButton.slots.length() && (i = commandButton.slots.get(i5)) != 6; i5++) {
                    if (z && i2 == -1 && i == 2) {
                        i2 = i4;
                        break;
                    }
                    if (z2 && i3 == -1 && i == 3) {
                        i3 = i4;
                        break;
                    }
                }
            }
        }
        ImmutableList.Builder builder = ImmutableList.builder();
        if (i2 != -1) {
            builder.add(list.get(i2).convertToPredefinedCustomCommandButton(2));
        }
        if (i3 != -1) {
            builder.add(list.get(i3).convertToPredefinedCustomCommandButton(3));
        }
        for (int i6 = 0; i6 < list.size(); i6++) {
            CommandButton commandButton2 = list.get(i6);
            if (commandButton2.isEnabled && commandButton2.canExecuteAction() && i6 != i2 && i6 != i3 && commandButton2.slots.contains(6)) {
                builder.add(commandButton2.convertToPredefinedCustomCommandButton(6));
            }
        }
        return builder.build();
    }

    static boolean containsButtonForSlot(List<CommandButton> list, int i) {
        for (int i2 = 0; i2 < list.size(); i2++) {
            if (list.get(i2).slots.get(0) == i) {
                return true;
            }
        }
        return false;
    }

    static ImmutableList<CommandButton> getMediaButtonPreferencesFromCustomLayout(List<CommandButton> list, Player.Commands commands, Bundle bundle) {
        if (list.isEmpty()) {
            return ImmutableList.of();
        }
        boolean zContainsAny = commands.containsAny(7, 6);
        boolean zContainsAny2 = commands.containsAny(9, 8);
        boolean z = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_PREVIOUS", false);
        boolean z2 = bundle.getBoolean("android.media.playback.ALWAYS_RESERVE_SPACE_FOR.ACTION_SKIP_TO_NEXT", false);
        int i = (zContainsAny || z) ? -1 : 0;
        int i2 = (zContainsAny2 || z2) ? -1 : i == 0 ? 1 : 0;
        ImmutableList.Builder builder = ImmutableList.builder();
        for (int i3 = 0; i3 < list.size(); i3++) {
            CommandButton commandButton = list.get(i3);
            if (i3 == i) {
                if (i2 == -1) {
                    builder.add(commandButton.copyWithSlots(ImmutableIntArray.of(2, 6)));
                } else {
                    builder.add(commandButton.copyWithSlots(ImmutableIntArray.of(2, 3, 6)));
                }
            } else if (i3 == i2) {
                builder.add(commandButton.copyWithSlots(ImmutableIntArray.of(3, 6)));
            } else {
                builder.add(commandButton.copyWithSlots(ImmutableIntArray.of(6)));
            }
        }
        return builder.build();
    }

    static boolean isPredefinedCustomCommandButtonCode(String str) {
        return isPredefinedPlayerCustomCommandButtonCode(str) || isPredefinedSessionCustomCommandButtonCode(str);
    }

    static CommandButton convertFromPredefinedCustomCommand(SessionCommand sessionCommand) {
        if (isPredefinedPlayerCustomCommandButtonCode(sessionCommand.customAction)) {
            int predefinedCustomCommandCode = getPredefinedCustomCommandCode(sessionCommand.customAction, CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX);
            return new Builder(0).setPlayerCommand(predefinedCustomCommandCode, getParameterFromBundle(sessionCommand.customExtras, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY, getParameterTypeForPlayerCommand(predefinedCustomCommandCode))).build();
        }
        int predefinedCustomCommandCode2 = getPredefinedCustomCommandCode(sessionCommand.customAction, CUSTOM_COMMAND_SESSION_COMMAND_PREFIX);
        return new Builder(0).setSessionCommand(new SessionCommand(predefinedCustomCommandCode2), getParameterFromBundle(sessionCommand.customExtras, CUSTOM_COMMAND_PARAMETER_EXTRAS_KEY, getParameterTypeForSessionCommand(predefinedCustomCommandCode2))).build();
    }

    private static boolean isPredefinedPlayerCustomCommandButtonCode(String str) {
        return str.startsWith(CUSTOM_COMMAND_PLAYER_COMMAND_PREFIX);
    }

    private static boolean isPredefinedSessionCustomCommandButtonCode(String str) {
        return str.startsWith(CUSTOM_COMMAND_SESSION_COMMAND_PREFIX);
    }

    private static int getPredefinedCustomCommandCode(String str, String str2) {
        return Integer.parseInt(str.substring(str2.length()));
    }

    /* JADX INFO: Access modifiers changed from: private */
    public static Object verifyParameterType(Object obj, int i) {
        if (obj == null) {
            return null;
        }
        switch (i) {
            case 1:
                if (obj instanceof Integer) {
                    obj = Long.valueOf(((Integer) obj).longValue());
                }
                Preconditions.checkArgument(obj instanceof Long, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 2:
                Preconditions.checkArgument(obj instanceof Integer, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 3:
                Preconditions.checkArgument(obj instanceof Boolean, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 4:
                if (obj instanceof Double) {
                    obj = Float.valueOf(((Double) obj).floatValue());
                }
                Preconditions.checkArgument(obj instanceof Float, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 5:
                Preconditions.checkArgument(obj instanceof Rating, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 6:
                Preconditions.checkArgument(obj instanceof MediaItem, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 7:
                Preconditions.checkArgument(obj instanceof MediaMetadata, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            case 8:
                Preconditions.checkArgument(obj instanceof TrackSelectionParameters, INCORRECT_PARAMETER_TYPE_MESSAGE);
                return obj;
            default:
                return null;
        }
    }

    @RequiresNonNull({"parameter"})
    private void writeParameterToBundle(Bundle bundle, String str) {
        int parameterTypeForPlayerCommand;
        SessionCommand sessionCommand = this.sessionCommand;
        if (sessionCommand != null) {
            parameterTypeForPlayerCommand = getParameterTypeForSessionCommand(sessionCommand.commandCode);
        } else {
            parameterTypeForPlayerCommand = getParameterTypeForPlayerCommand(this.playerCommand);
        }
        switch (parameterTypeForPlayerCommand) {
            case 1:
                bundle.putLong(str, ((Long) this.parameter).longValue());
                break;
            case 2:
                bundle.putInt(str, ((Integer) this.parameter).intValue());
                break;
            case 3:
                bundle.putBoolean(str, ((Boolean) this.parameter).booleanValue());
                break;
            case 4:
                bundle.putFloat(str, ((Float) this.parameter).floatValue());
                break;
            case 5:
                bundle.putBundle(str, ((Rating) this.parameter).toBundle());
                break;
            case 6:
                bundle.putBundle(str, ((MediaItem) this.parameter).toBundle());
                break;
            case 7:
                bundle.putBundle(str, ((MediaMetadata) this.parameter).toBundle());
                break;
            case 8:
                bundle.putBundle(str, ((TrackSelectionParameters) this.parameter).toBundle());
                break;
        }
    }

    private static Object getParameterFromBundle(Bundle bundle, String str, int i) {
        if (!bundle.containsKey(str)) {
            return null;
        }
        switch (i) {
            case 1:
                return Long.valueOf(bundle.getLong(str));
            case 2:
                return Integer.valueOf(bundle.getInt(str));
            case 3:
                return Boolean.valueOf(bundle.getBoolean(str));
            case 4:
                return Float.valueOf(bundle.getFloat(str));
            case 5:
                return Rating.fromBundle((Bundle) Preconditions.checkNotNull(bundle.getBundle(str)));
            case 6:
                return MediaItem.fromBundle((Bundle) Preconditions.checkNotNull(bundle.getBundle(str)));
            case 7:
                return MediaMetadata.fromBundle((Bundle) Preconditions.checkNotNull(bundle.getBundle(str)));
            case 8:
                return TrackSelectionParameters.fromBundle((Bundle) Preconditions.checkNotNull(bundle.getBundle(str)));
            default:
                return null;
        }
    }
}
