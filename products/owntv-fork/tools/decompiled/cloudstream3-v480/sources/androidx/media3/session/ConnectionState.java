package androidx.media3.session;

import android.app.PendingIntent;
import android.os.Binder;
import android.os.Bundle;
import android.os.IBinder;
import androidx.core.app.BundleCompat;
import androidx.media3.common.Player;
import androidx.media3.common.util.BundleCollectionUtil;
import androidx.media3.common.util.Util;
import com.google.common.base.Function;
import com.google.common.base.Preconditions;
import com.google.common.collect.ImmutableList;
import java.util.ArrayList;

/* JADX INFO: loaded from: classes4.dex */
class ConnectionState {
    public final ImmutableList<CommandButton> commandButtonsForMediaItems;
    public final ImmutableList<CommandButton> customLayout;
    public final int libraryVersion;
    public final ImmutableList<CommandButton> mediaButtonPreferences;
    public final android.media.session.MediaSession.Token platformToken;
    public final Player.Commands playerCommandsFromPlayer;
    public final Player.Commands playerCommandsFromSession;
    public final PlayerInfo playerInfo;
    public final PendingIntent sessionActivity;
    public final IMediaSession sessionBinder;
    public final SessionCommands sessionCommands;
    public final Bundle sessionExtras;
    public final int sessionInterfaceVersion;
    public final Bundle tokenExtras;
    private static final String FIELD_LIBRARY_VERSION = Util.intToStringMaxRadix(0);
    private static final String FIELD_SESSION_BINDER = Util.intToStringMaxRadix(1);
    private static final String FIELD_SESSION_ACTIVITY = Util.intToStringMaxRadix(2);
    private static final String FIELD_CUSTOM_LAYOUT = Util.intToStringMaxRadix(9);
    private static final String FIELD_MEDIA_BUTTON_PREFERENCES = Util.intToStringMaxRadix(14);
    private static final String FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS = Util.intToStringMaxRadix(13);
    private static final String FIELD_SESSION_COMMANDS = Util.intToStringMaxRadix(3);
    private static final String FIELD_PLAYER_COMMANDS_FROM_SESSION = Util.intToStringMaxRadix(4);
    private static final String FIELD_PLAYER_COMMANDS_FROM_PLAYER = Util.intToStringMaxRadix(5);
    private static final String FIELD_TOKEN_EXTRAS = Util.intToStringMaxRadix(6);
    private static final String FIELD_SESSION_EXTRAS = Util.intToStringMaxRadix(11);
    private static final String FIELD_PLAYER_INFO = Util.intToStringMaxRadix(7);
    private static final String FIELD_SESSION_INTERFACE_VERSION = Util.intToStringMaxRadix(8);
    private static final String FIELD_IN_PROCESS_BINDER = Util.intToStringMaxRadix(10);
    private static final String FIELD_PLATFORM_TOKEN = Util.intToStringMaxRadix(12);

    public ConnectionState(int i, int i2, IMediaSession iMediaSession, PendingIntent pendingIntent, ImmutableList<CommandButton> immutableList, ImmutableList<CommandButton> immutableList2, ImmutableList<CommandButton> immutableList3, SessionCommands sessionCommands, Player.Commands commands, Player.Commands commands2, Bundle bundle, Bundle bundle2, PlayerInfo playerInfo, android.media.session.MediaSession.Token token) {
        this.libraryVersion = i;
        this.sessionInterfaceVersion = i2;
        this.sessionBinder = iMediaSession;
        this.sessionActivity = pendingIntent;
        this.customLayout = immutableList;
        this.mediaButtonPreferences = immutableList2;
        this.commandButtonsForMediaItems = immutableList3;
        this.sessionCommands = sessionCommands;
        this.playerCommandsFromSession = commands;
        this.playerCommandsFromPlayer = commands2;
        this.tokenExtras = bundle;
        this.sessionExtras = bundle2;
        this.playerInfo = playerInfo;
        this.platformToken = token;
    }

    public Bundle toBundleForRemoteProcess(int i) {
        Bundle bundle = new Bundle();
        bundle.putInt(FIELD_LIBRARY_VERSION, this.libraryVersion);
        BundleCompat.putBinder(bundle, FIELD_SESSION_BINDER, this.sessionBinder.asBinder());
        bundle.putParcelable(FIELD_SESSION_ACTIVITY, this.sessionActivity);
        if (!this.customLayout.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(this.customLayout, new ConnectionState$$ExternalSyntheticLambda3()));
        }
        if (!this.mediaButtonPreferences.isEmpty()) {
            ImmutableList<CommandButton> immutableList = this.mediaButtonPreferences;
            if (i >= 7) {
                bundle.putParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES, BundleCollectionUtil.toBundleArrayList(immutableList, new ConnectionState$$ExternalSyntheticLambda3()));
            } else {
                bundle.putParcelableArrayList(FIELD_CUSTOM_LAYOUT, BundleCollectionUtil.toBundleArrayList(CommandButton.getCustomLayoutFromMediaButtonPreferences(immutableList, true, true), new ConnectionState$$ExternalSyntheticLambda3()));
            }
        }
        if (!this.commandButtonsForMediaItems.isEmpty()) {
            bundle.putParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS, BundleCollectionUtil.toBundleArrayList(this.commandButtonsForMediaItems, new ConnectionState$$ExternalSyntheticLambda3()));
        }
        bundle.putBundle(FIELD_SESSION_COMMANDS, this.sessionCommands.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION, this.playerCommandsFromSession.toBundle());
        bundle.putBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER, this.playerCommandsFromPlayer.toBundle());
        bundle.putBundle(FIELD_TOKEN_EXTRAS, this.tokenExtras);
        bundle.putBundle(FIELD_SESSION_EXTRAS, this.sessionExtras);
        bundle.putBundle(FIELD_PLAYER_INFO, this.playerInfo.filterByAvailableCommands(MediaUtils.intersect(this.playerCommandsFromSession, this.playerCommandsFromPlayer), false, false).toBundleForRemoteProcess(i));
        bundle.putInt(FIELD_SESSION_INTERFACE_VERSION, this.sessionInterfaceVersion);
        android.media.session.MediaSession.Token token = this.platformToken;
        if (token != null) {
            bundle.putParcelable(FIELD_PLATFORM_TOKEN, token);
        }
        return bundle;
    }

    public Bundle toBundleInProcess() {
        Bundle bundle = new Bundle();
        bundle.putBinder(FIELD_IN_PROCESS_BINDER, new InProcessBinder());
        return bundle;
    }

    public static ConnectionState fromBundle(Bundle bundle) {
        ImmutableList immutableListOf;
        ImmutableList immutableListOf2;
        ImmutableList immutableListOf3;
        SessionCommands sessionCommandsFromBundle;
        Player.Commands commandsFromBundle;
        Player.Commands commandsFromBundle2;
        PlayerInfo playerInfoFromBundle;
        IBinder binder = bundle.getBinder(FIELD_IN_PROCESS_BINDER);
        if (binder instanceof InProcessBinder) {
            return ((InProcessBinder) binder).getConnectionState();
        }
        int i = bundle.getInt(FIELD_LIBRARY_VERSION, 0);
        final int i2 = bundle.getInt(FIELD_SESSION_INTERFACE_VERSION, 0);
        IBinder iBinder = (IBinder) Preconditions.checkNotNull(BundleCompat.getBinder(bundle, FIELD_SESSION_BINDER));
        PendingIntent pendingIntent = (PendingIntent) bundle.getParcelable(FIELD_SESSION_ACTIVITY);
        ArrayList parcelableArrayList = bundle.getParcelableArrayList(FIELD_CUSTOM_LAYOUT);
        if (parcelableArrayList != null) {
            immutableListOf = BundleCollectionUtil.fromBundleList(new Function() { // from class: androidx.media3.session.ConnectionState$$ExternalSyntheticLambda0
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return CommandButton.fromBundle((Bundle) obj, i2);
                }
            }, parcelableArrayList);
        } else {
            immutableListOf = ImmutableList.of();
        }
        ImmutableList immutableList = immutableListOf;
        ArrayList parcelableArrayList2 = bundle.getParcelableArrayList(FIELD_MEDIA_BUTTON_PREFERENCES);
        if (parcelableArrayList2 != null) {
            immutableListOf2 = BundleCollectionUtil.fromBundleList(new Function() { // from class: androidx.media3.session.ConnectionState$$ExternalSyntheticLambda1
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return CommandButton.fromBundle((Bundle) obj, i2);
                }
            }, parcelableArrayList2);
        } else {
            immutableListOf2 = ImmutableList.of();
        }
        ImmutableList immutableList2 = immutableListOf2;
        ArrayList parcelableArrayList3 = bundle.getParcelableArrayList(FIELD_COMMAND_BUTTONS_FOR_MEDIA_ITEMS);
        if (parcelableArrayList3 != null) {
            immutableListOf3 = BundleCollectionUtil.fromBundleList(new Function() { // from class: androidx.media3.session.ConnectionState$$ExternalSyntheticLambda2
                @Override // com.google.common.base.Function
                public final Object apply(Object obj) {
                    return CommandButton.fromBundle((Bundle) obj, i2);
                }
            }, parcelableArrayList3);
        } else {
            immutableListOf3 = ImmutableList.of();
        }
        ImmutableList immutableList3 = immutableListOf3;
        Bundle bundle2 = bundle.getBundle(FIELD_SESSION_COMMANDS);
        if (bundle2 == null) {
            sessionCommandsFromBundle = SessionCommands.EMPTY;
        } else {
            sessionCommandsFromBundle = SessionCommands.fromBundle(bundle2);
        }
        SessionCommands sessionCommands = sessionCommandsFromBundle;
        Bundle bundle3 = bundle.getBundle(FIELD_PLAYER_COMMANDS_FROM_PLAYER);
        if (bundle3 == null) {
            commandsFromBundle = Player.Commands.EMPTY;
        } else {
            commandsFromBundle = Player.Commands.fromBundle(bundle3);
        }
        Player.Commands commands = commandsFromBundle;
        Bundle bundle4 = bundle.getBundle(FIELD_PLAYER_COMMANDS_FROM_SESSION);
        if (bundle4 == null) {
            commandsFromBundle2 = Player.Commands.EMPTY;
        } else {
            commandsFromBundle2 = Player.Commands.fromBundle(bundle4);
        }
        Player.Commands commands2 = commandsFromBundle2;
        Bundle bundleConvertToNullIfInvalid = Util.convertToNullIfInvalid(bundle.getBundle(FIELD_TOKEN_EXTRAS));
        Bundle bundleConvertToNullIfInvalid2 = Util.convertToNullIfInvalid(bundle.getBundle(FIELD_SESSION_EXTRAS));
        Bundle bundle5 = bundle.getBundle(FIELD_PLAYER_INFO);
        if (bundle5 == null) {
            playerInfoFromBundle = PlayerInfo.DEFAULT;
        } else {
            playerInfoFromBundle = PlayerInfo.fromBundle(bundle5, i2);
        }
        PlayerInfo playerInfo = playerInfoFromBundle;
        android.media.session.MediaSession.Token token = (android.media.session.MediaSession.Token) bundle.getParcelable(FIELD_PLATFORM_TOKEN);
        Bundle bundle6 = bundleConvertToNullIfInvalid2;
        IMediaSession iMediaSessionAsInterface = IMediaSession.Stub.asInterface(iBinder);
        if (bundleConvertToNullIfInvalid == null) {
            bundleConvertToNullIfInvalid = Bundle.EMPTY;
        }
        Bundle bundle7 = bundleConvertToNullIfInvalid;
        if (bundle6 == null) {
            bundle6 = Bundle.EMPTY;
        }
        return new ConnectionState(i, i2, iMediaSessionAsInterface, pendingIntent, immutableList, immutableList2, immutableList3, sessionCommands, commands2, commands, bundle7, bundle6, playerInfo, token);
    }

    private final class InProcessBinder extends Binder {
        private InProcessBinder() {
        }

        public ConnectionState getConnectionState() {
            return ConnectionState.this;
        }
    }
}
