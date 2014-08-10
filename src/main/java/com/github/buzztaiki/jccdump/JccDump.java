package com.github.buzztaiki.jccdump;

import sun.jvm.hotspot.code.CodeBlob;
import sun.jvm.hotspot.code.CodeCache;
import sun.jvm.hotspot.code.CodeCacheVisitor;
import sun.jvm.hotspot.debugger.Address;
import sun.jvm.hotspot.runtime.VM;
import sun.jvm.hotspot.tools.Tool;

public class JccDump extends Tool {
    @Override
    public void run() {
        VM vm = VM.getVM();
        CodeCache codeCache = vm.getCodeCache();
         printAllBlobs(codeCache);
    }
    private void printAllBlobs(CodeCache codeCache) {
        System.out.format("name\tsize\tcontentBegin%n");
        codeCache.iterate(new CodeCacheVisitor() {
            @Override
            public void visit(CodeBlob blob) {
                try {
                    System.out.format("%s\t%d\t%s%n",
                            blob.getName(),
                            blob.getSize(),
                            blob.contentBegin());
                } catch (NullPointerException e) {
                    // pass
                }
            }
            @Override
            public void prologue(Address arg0, Address arg1) {
            }
            @Override
            public void epilogue() {
            }
        });
    }
    public void startAndStop(String[] args) {
        start(args);
        stop();
    }
}
