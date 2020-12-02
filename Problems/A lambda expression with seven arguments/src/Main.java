class Seven {

    public static MultipleArgumentsLambda.SeptenaryStringFunction fun =
    (s1, s2, s3, s4, s5, s6, s7) -> new StringBuilder()
        .append(s1).append(s2).append(s3).append(s4).append(s5).append(s6).append(s7)
        .toString().toUpperCase();
}