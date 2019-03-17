package edu.kit.nfcrypto;

import android.content.Context;
import android.content.res.Resources;
import android.support.v4.util.Pair;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.internal.matchers.Null;
import org.mockito.junit.MockitoJUnitRunner;
import java.util.ArrayList;

import edu.kit.nfcrypto.data.Mode;
import edu.kit.nfcrypto.keys.Key;

import static org.junit.Assert.*;
import static org.powermock.api.mockito.PowerMockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserTest {
    private String namePLA = "Klartext";
    private String nameCES = "Cäsar";
    private String nameVIG = "Minikey";
    private String nameAES = "AES";

    private final String FREISCHALTEN = "Neues freischalten!";

    private User user = User.getInstance();

    @Mock
    private Context context;
    @Mock
    private Resources resource;

    @Before
    public void setUp() {
        //Mock context
        when(context.getResources()).thenReturn(resource);
        when(resource.getString(R.string.plaintext)).thenReturn(namePLA);
        when(resource.getString(R.string.cesar)).thenReturn(nameCES);
        when(resource.getString(R.string.vigenere)).thenReturn(nameVIG);
        when(resource.getString(R.string.aes)).thenReturn(nameAES);
    }

    @Test
    public void getInstance() {
        assertSame(user,User.getInstance());
    }

    @Test
    public void getInstanceNull() {assertFalse(User.getInstance().equals(null));}

    @Test
    public void permissionNone() {
        boolean[] permissionArray = new boolean[]{false,false,false,false};
        //setPermission
        user.setPermission(permissionArray);
        //getPermission
        ArrayList<String> nameString = new ArrayList<String>();
        ArrayList<Mode> modeString = new ArrayList<Mode>();
        nameString.add("Neues freischalten!");
        Pair<ArrayList<String>, ArrayList<Mode>> permissionPair = new Pair<ArrayList<String>, ArrayList<Mode>>(nameString, modeString);
        //getPermissionArray
        assertEquals(permissionPair, user.getPermissionArray(context));
        //addPermission
        assertTrue(user.addPermission(Mode.CES));
    }

    @Test
    public void permissionAll() {
        boolean[] permissionArray = new boolean[]{true,true,true,true};
        //setPermission
        user.setPermission(permissionArray);
        //getPermission
        ArrayList<String> nameString = new ArrayList<String>();
        ArrayList<Mode> modeString = new ArrayList<Mode>();
        nameString.add(namePLA);
        modeString.add(Mode.PLA);
        nameString.add(nameCES);
        modeString.add(Mode.CES);
        nameString.add(nameVIG);
        modeString.add(Mode.VIG);
        nameString.add(nameAES);
        modeString.add(Mode.AES);
        Pair<ArrayList<String>, ArrayList<Mode>> permissionPair = new Pair<ArrayList<String>, ArrayList<Mode>>(nameString, modeString);
        //getPermissionArray
        assertEquals(permissionPair, user.getPermissionArray(context));
        //addPermission
        assertFalse(user.addPermission(Mode.PLA));
        assertFalse(user.addPermission(Mode.CES));
        assertFalse(user.addPermission(Mode.VIG));
        assertFalse(user.addPermission(Mode.AES));
    }

    @Mock
    private Key key;

    @Test
    public void lastKey() {
        user.setLastKey(key);
        assertSame(key,user.getLastKey());
    }
}