This test ensures the rpg can handle non-starting terminals that contain angle brackets

{
<start>
This should print these brackets: <non-terminal>
}

{
<non-terminal>
< > > < < >>><
}
