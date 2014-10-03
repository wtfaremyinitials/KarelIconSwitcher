KarelIconSwitcher.java
===

So my CompSci teacher said changing the Karel icon couldn't be done. He was wrong. :smile:

Usage
===

```java

// Get an instance of the KarelIconSwitcher object
KarelIconSwitcher switcher = KarelIconSwitcher.getInstance();

// Set the icon of karel facing north while on to 'newkarel.png'
switcher.changeIcon(KarelIconSwitcher.KarelIcon.KAREL_NORTH_ON, "newkarel.png");

```

There are 6 icons that can (currently) be replaced.

- KarelIconSwitcher.KarelIcon.KAREL_NORTH_ON
- KarelIconSwitcher.KarelIcon.KAREL_SOUTH_ON
- KarelIconSwitcher.KarelIcon.KAREL_EAST_ON
- KarelIconSwitcher.KarelIcon.KAREL_WEST_ON
- KarelIconSwitcher.KarelIcon.KAREL_NORTH_OFF
- KarelIconSwitcher.KarelIcon.KAREL_SOUTH_OFF
- KarelIconSwitcher.KarelIcon.KAREL_EAST_OFF
- KarelIconSwitcher.KarelIcon.KAREL_WEST_OFF