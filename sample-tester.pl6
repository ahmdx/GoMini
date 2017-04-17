sub compileFiles() {
    my @files = ["Go", "Go2", "Go3", "Go4", "Go5", "Go6", "Go7", "Go8", "Go9", "Go10"];
    # shell "rm -v out/production/GoCompiler/JLex/*.class";

    # shell "javac -d out/production/GoCompiler/JLex -cp out/production/GoCompiler $*CWD/src/JLex/Main.java";

    shell "javac -d bin/JLex -cp bin $*CWD/src/JLex/*.java";
    for @files.hyper -> $i {
        say "$i";
        shell "java -cp bin JLex.MLA $i.in $i.out2";
    }
}

sub check($file1, $file2) {
    my $result = run "diff", "$file1", "$file2" , "-q" , :out;
    if $result.out.slurp-rest.contains("differ") {
        return False;
    } else {
        return True;
    }
}

sub runTests() {
    my $promise1 = start check("Go.out", "Go.out2");
    my $promise2 = start check("Go2.out", "Go2.out2");
    my $promise3 = start check("Go3.out", "Go3.out2");
    my $promise4 = start check("Go4.out", "Go4.out2");
    my $promise5 = start check("Go5.out", "Go5.out2");
    my $promise6 = start check("Go6.out", "Go6.out2");
    my $promise7 = start check("Go7.out", "Go7.out2");
    my $promise8 = start check("Go8.out", "Go8.out2");
    my $promise9 = start check("Go9.out", "Go9.out2");
    my $promise10 = start check("Go10.out", "Go10.out2");


    my $result1 = await $promise1;
    my $result2 = await $promise2;
    my $result3 = await $promise3;
    my $result4 = await $promise4;
    my $result5 = await $promise5;
    my $result6 = await $promise6;
    my $result7 = await $promise7;
    my $result8 = await $promise8;
    my $result9 = await $promise9;
    my $result10 = await $promise10;


    say "Sample 1 expected and actual similar? $result1";
    say "Sample 2 expected and actual similar? $result2";
    say "Sample 3 expected and actual similar? $result3";
    say "Sample 4 expected and actual similar? $result4";
    say "Sample 5 expected and actual similar? $result5";
    say "Sample 6 expected and actual similar? $result6";
    say "Sample 7 expected and actual similar? $result7";
    say "Sample 8 expected and actual similar? $result8";
    say "Sample 9 expected and actual similar? $result9";
    say "Sample 10 expected and actual similar? $result10";
}
compileFiles();
runTests();
