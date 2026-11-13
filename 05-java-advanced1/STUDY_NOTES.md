# Java 上級1

Javaのマルチスレッドと並行処理について学習します。

## 学習状況

- [x] プロセスとスレッドの概要
- [x] スレッドの生成と実行
- [x] スレッドの制御とライフサイクル1
- [x] スレッドの制御とライフサイクル2
- [x] メモリの可視性
- [x] 同期処理 - synchronized
- [x] 高度な同期処理 - concurrent.Lock
- [x] 生産者・消費者問題1
- [x] 生産者・消費者問題2
- [x] CAS - 同期処理とアトミック操作
- [x] 並行処理コレクション
- [ ] スレッドプールとExecutorフレームワーク1
- [ ] スレッドプールとExecutorフレームワーク2

---

## 1. プロセスとスレッドの概要

### マルチタスク（Multitasking）

- マルチタスクは、複数のタスクを並行して処理する仕組み
- タスクとは、コンピュータが実行する作業や処理のこと
- 例えば、音楽を再生しながらWebブラウザを使用するような処理が該当する
- CPUのコアが1つの場合でも、短い時間でタスクを切り替えることで複数の処理を進められる
- CPUのコアが複数ある場合は、複数のタスクを実際に同時実行できる
- マルチタスクによって、コンピュータの応答性やCPUの利用効率を向上させられる

### マルチプロセス（Multiprocessing）

- マルチプロセスは、複数のプロセスを実行する仕組み
- プロセスは、OSによって管理される実行中のプログラム
- 各プロセスは基本的に独立したメモリ空間を持つ
- 一つのプロセスで問題が発生しても、他のプロセスへの影響を抑えやすい
- 異なるプロセス間でデータを共有する場合は、プロセス間通信（IPC）などの仕組みが必要になる
- プロセスごとにメモリやリソースを管理するため、一定の管理コストが発生する

### プロセス（Process）

- プロセスは、実行中のプログラムを管理する単位
- プログラムを実行すると、OSによってプロセスが生成される
- プロセスは、コードやデータ、ヒープ、スタックなどを利用して処理を実行する
- プロセスごとに基本的に独立した仮想アドレス空間が用意される
- 一つのプロセスには、少なくとも一つの実行スレッドが存在する
- プロセス内では、複数のスレッドを生成して処理を実行できる

### スレッド（Thread）

- スレッドは、プロセス内で実際に処理を実行する単位
- 一つのプロセス内で複数のスレッドを実行できる
- スレッドは、同じプロセス内の他のスレッドとヒープ領域などを共有する
- 各スレッドは、独立したスタック領域とプログラムの実行状態を持つ
- スレッドを利用すると、一つのアプリケーション内で複数の処理を並行して進められる
- Javaでは、`Thread`クラスなどを利用してスレッドを扱うことができる
- 通常のJavaプログラムでは、`main()`メソッドを実行するメインスレッドが使用される

### プロセスとスレッドの違い

| 比較項目 | プロセス | スレッド |
| --- | --- | --- |
| 基本概念 | 実行中のプログラムを管理する単位 | プロセス内の実行単位 |
| メモリ空間 | 基本的に独立 | 同じプロセス内で一部を共有 |
| ヒープ領域 | プロセスごとに管理 | 同一プロセス内で共有 |
| スタック領域 | 各スレッドのスタックを含む | スレッドごとに独立 |
| データ共有 | プロセス間通信などが必要 | 共有メモリを利用できる |
| 主な特徴 | 独立性が高い | データを共有しながら処理できる |

### マルチスレッド（Multithreading）

- マルチスレッドは、一つのプロセス内で複数のスレッドを実行する仕組み
- 複数のスレッドを利用することで、異なる処理を並行して実行できる
- CPUコアが複数ある場合は、複数のスレッドを同時に実行できる
- 同じプロセス内のスレッドはメモリを共有するため、データの受け渡しが比較的容易になる
- 一方で、複数のスレッドが同じデータを変更すると、データ競合が発生する可能性がある
- 共有データを安全に扱うためには、同期処理が必要になる場合がある
- マルチスレッドは、Webサーバーやバックグラウンド処理などで利用される

### 並行処理（Concurrency）と並列処理（Parallelism）

**並行処理（Concurrency）**
- 複数の処理を、同じ時間帯に進行させること
- CPUコアが1つでも、処理を切り替えることで実現できる
- 複数の処理が必ずしも同じ瞬間に実行されているわけではない

**並列処理（Parallelism）**
- 複数の処理を、実際に同じ瞬間に実行すること
- 複数のCPUコアなどの実行リソースを利用する
- 処理内容によっては、全体の実行時間を短縮できる

### スレッドスケジューリング（Thread Scheduling）

- スレッドスケジューリングは、CPUで実行するスレッドを決定する仕組み
- OSのスケジューラが、実行可能なスレッドの中から実行対象を選択する
- 複数のスレッドが存在する場合、CPUの実行時間を分配する
- CPUコア数よりスレッド数が多い場合は、複数のスレッドがCPUの実行機会を分け合う
- スレッドの実行順序は、OSのスケジューリングなどによって変化する
- プログラムでスレッドを生成した順序と、実際に処理が実行される順序は必ずしも一致しない
- スレッドの実行順序を前提とした処理を作成する場合は注意が必要になる

### タイムスライス（Time Slice）

- タイムスライスは、スレッドに割り当てられるCPUの実行時間
- OSは一定の条件やスケジューリング方針に従い、実行するスレッドを切り替える
- CPUコアが1つの場合でも、短い時間でスレッドを切り替えることで並行処理を実現できる
- 各スレッドはCPUの実行機会を得ながら処理を進める
- タイムスライスの長さや切り替えの条件は、OSやスケジューリング方式によって異なる
- 実行中のスレッドが待機状態になった場合なども、別のスレッドに切り替わることがある

### コンテキストスイッチ（Context Switch）

- コンテキストスイッチは、CPUで実行するスレッドやプロセスを切り替える処理
- 現在実行中のスレッドの状態を保存し、次に実行するスレッドの状態を復元する
- スレッドの実行状態には、プログラムカウンタやCPUレジスタなどの情報が含まれる
- 保存した状態を復元することで、途中まで実行していた処理を再開できる
- CPUの実行時間を複数のスレッドに割り当てるために重要な仕組み
- コンテキストスイッチには一定の処理コストが発生する
- 切り替えが頻繁に発生すると、実際の処理に使用できるCPU時間が減少する場合がある

### コンテキストスイッチの流れ

1. 現在のスレッドがCPUで処理を実行する
2. タイムスライスの終了や待機などによって、スレッドの切り替えが必要になる
3. OSが現在のスレッドの実行状態を保存する
4. 次に実行するスレッドを選択する
5. 選択されたスレッドの実行状態を復元する
6. 新しいスレッドがCPUで処理を再開する

### コンテキストスイッチのコスト

- コンテキストスイッチでは、スレッドの実行状態を保存・復元する処理が必要になる
- 切り替え処理中は、本来のアプリケーション処理以外の作業が発生する
- 頻繁な切り替えは、CPUキャッシュの利用効率などにも影響する場合がある
- スレッド数が非常に多い場合、スケジューリングや切り替えの負荷が増加する可能性がある
- スレッド数を増やしても、必ず処理性能が向上するわけではない
- CPUコア数や処理内容に応じて、適切なスレッドの利用方法を考える必要がある

### CPUバウンドとI/Oバウンド

**CPUバウンド（CPU-bound）**
- 計算処理など、CPUの処理能力が実行速度に大きく影響する処理
- CPUを使用する時間が長い
- CPUコア数を超えてスレッド数を増やしても、性能が向上しない場合がある

**I/Oバウンド（I/O-bound）**
- ファイルの読み書きやネットワーク通信など、入出力の待機時間が大きい処理
- I/O待機中は、CPUが別の処理を実行できる場合がある
- 複数のスレッドを利用することで、待機時間を活用できる場合がある

### プロセスとスレッドのまとめ

- プロセスは実行中のプログラムを管理する単位であり、スレッドはその内部で処理を実行する単位
- マルチタスクは、複数のタスクを並行して進める仕組み
- マルチプロセスは、複数のプロセスを実行する仕組み
- マルチスレッドは、一つのプロセス内で複数のスレッドを実行する仕組み
- 同一プロセス内のスレッドはヒープ領域などを共有し、スタック領域はスレッドごとに独立している
- 並行処理は複数の処理を同じ時間帯に進行させ、並列処理は複数の処理を実際に同時実行する
- スレッドの実行順序はOSのスケジューリングなどによって決定され、基本的に保証されない
- コンテキストスイッチによって、スレッドの実行状態を保存・復元しながら処理を切り替えられる
- コンテキストスイッチには処理コストが発生するため、スレッド数を増やせば必ず高速になるわけではない
- マルチスレッドによる共有データの問題を理解することが、今後学習する同期処理の基礎になる

## 2. スレッドの生成と実行

### Javaにおけるスレッドの基本概念

- Javaでは、`Thread`クラスを利用して新しいスレッドを生成できる
- スレッドを利用することで、複数の処理を並行して実行できる
- Javaプログラムの実行開始時には、通常`main`スレッドが`main()`メソッドを実行する
- 新しいスレッドを生成すると、`main`スレッドとは別の実行経路で処理を進められる
- 複数のスレッドは、それぞれ独立したスタック領域を持つ
- 一方で、同じプロセス内のスレッドはヒープ領域などを共有する
- スレッドの実行順序は保証されず、OSのスケジューリングなどによって変化する

### Threadクラスによるスレッドの生成

- `Thread`クラスは、Javaでスレッドを扱うための基本的なクラス
- `Thread`クラスを継承し、`run()`メソッドをオーバーライドすることで処理を定義できる
- `run()`メソッドには、新しいスレッドで実行したい処理を記述する
- `start()`メソッドを呼び出すことで、新しいスレッドの実行を開始する

**コード例**

```java
public class ThreadStartMain {

    public static void main(String[] args) {

        Thread thread = new MyThread();
        thread.start();

        System.out.println("mainスレッド終了");
    }

    static class MyThread extends Thread {

        @Override
        public void run() {
            System.out.println("新しいスレッド実行");
        }
    }
}
```

- `MyThread`は`Thread`クラスを継承している
- `run()`メソッドをオーバーライドして実行処理を定義している
- `new MyThread()`によってスレッドオブジェクトを生成する
- `start()`によって新しいスレッドの実行を開始する
- `main`スレッドと`MyThread`は、それぞれ独立して処理を進める

### start()とrun()の違い

**start()メソッド**

- 新しいスレッドの実行を開始するためのメソッド
- JVMにスレッドの実行開始を要求する
- 新しいスレッドで`run()`メソッドが実行される
- `start()`を呼び出した直後に`run()`の処理が始まるとは限らない
- 同じ`Thread`インスタンスに対して`start()`を2回呼び出すと`IllegalThreadStateException`が発生する

**run()メソッド**

- スレッドで実行する処理を定義するメソッド
- `run()`を直接呼び出しても、新しいスレッドは生成されない
- 通常のメソッド呼び出しと同じように、呼び出したスレッドで処理が実行される

**コード例**

```java
public class StartVsRunMain {

    public static void main(String[] args) {

        Thread thread1 = new Thread(() -> {
            System.out.println(
                    Thread.currentThread().getName()
            );
        });

        thread1.run();

        Thread thread2 = new Thread(() -> {
            System.out.println(
                    Thread.currentThread().getName()
            );
        });

        thread2.start();
    }
}
```

**出力例**

```text
main
Thread-0
```

※ 自動生成されるスレッド名は実行環境などによって異なる。

- `thread1.run()`は`main`スレッドで実行される
- `thread2.start()`は新しいスレッドで実行される
- マルチスレッド処理を実現する場合は、基本的に`start()`を使用する

### mainスレッドと新しいスレッド

- `main()`メソッドを実行するスレッドを`main`スレッドと呼ぶ
- `main`スレッドから新しいスレッドを生成できる
- 新しいスレッドを開始しても、`main`スレッドの処理は基本的に継続する
- `start()`は新しいスレッドの処理完了を待つメソッドではない
- `main()`メソッドが終了しても、実行中の一般スレッドが残っていれば通常JVMは終了しない

### Thread.currentThread()

- `Thread.currentThread()`は、現在処理を実行しているスレッドを取得する静的メソッド
- 戻り値は`Thread`オブジェクト
- 取得したスレッドから、名前や状態などの情報を確認できる
- マルチスレッド環境で、どのスレッドが処理しているか確認する際に利用する

**コード例**

```java
Thread current = Thread.currentThread();

System.out.println(current.getName());
```

**主な関連メソッド**

| メソッド | 説明 |
| --- | --- |
| `currentThread()` | 現在実行中のスレッドを取得 |
| `getName()` | スレッド名を取得 |
| `setName()` | スレッド名を設定 |
| `getId()` | スレッドIDを取得 |
| `getState()` | スレッドの状態を取得 |
| `isDaemon()` | デーモンスレッドか確認 |

※ `getId()`はJava 19以降非推奨であり、スレッド識別子の取得には`threadId()`が推奨される。

### スレッド名の設定

- スレッドには名前を設定できる
- 名前を設定すると、ログやデバッグ時にスレッドを識別しやすくなる
- `setName()`やコンストラクタを利用して設定できる

**コード例**

```java
Thread thread = new Thread(() -> {
    System.out.println(
            Thread.currentThread().getName()
    );
}, "worker-1");

thread.start();
```

**出力例**

```text
worker-1
```

### スレッドの実行順序

- 複数のスレッドを開始しても、実行順序は保証されない
- スレッドの生成順序と実際の処理順序は異なる場合がある
- `start()`を先に呼び出したスレッドが、先に処理を完了するとは限らない
- OSのスケジューリングやCPUの状況などによって実行順序が変化する
- マルチスレッドプログラムでは、特定の実行順序に依存しない設計が重要になる

### Thread.sleep()

- `Thread.sleep()`は、現在実行中のスレッドを指定時間だけ一時停止する静的メソッド
- 停止時間は主にミリ秒単位で指定する
- 停止中は、基本的にCPUを使用して処理を進めない
- 指定した時間が経過しても、すぐに実行が再開されるとは限らない
- `sleep()`はスレッド間の正確な実行順序を保証するものではない
- `InterruptedException`が発生する可能性があるため、例外処理が必要になる

**コード例**

```java
public class SleepMain {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {

            System.out.println("処理開始");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("処理再開");
        });

        thread.start();
    }
}
```

- `Thread.sleep(1000)`は約1秒間の停止を要求する
- 停止するのは`Thread.sleep()`を実行したスレッド
- `main`スレッドなど、他のスレッドまで停止するわけではない
- 割り込みを受けた場合の処理については、スレッド制御の章で詳しく学習する

### デーモンスレッド（Daemon Thread）

- デーモンスレッドは、バックグラウンド処理などに利用されるスレッド
- 一般スレッド（User Thread）とは、JVMの終了条件に関する扱いが異なる
- すべての一般スレッドが終了すると、JVMはデーモンスレッドの完了を待たずに終了できる
- デーモンスレッドが実行中でも、JVMが終了する可能性がある
- デーモンスレッドは、終了時に処理が最後まで実行される保証がない
- 必ず完了させる必要がある重要な処理には、デーモンスレッドを安易に使用しない

### 一般スレッドとデーモンスレッドの違い

| 比較項目 | 一般スレッド | デーモンスレッド |
| --- | --- | --- |
| 主な役割 | 通常のアプリケーション処理 | バックグラウンド処理 |
| JVM終了への影響 | 実行中は通常JVMの終了を妨げる | 単独ではJVMの終了を妨げない |
| 処理完了 | JVMの通常終了前に実行継続できる | 完了前にJVMが終了する可能性がある |
| 設定方法 | 通常は一般スレッドとして生成 | `setDaemon(true)`などで設定 |

### setDaemon(true)

- `setDaemon(true)`によって、スレッドをデーモンスレッドに設定できる
- `setDaemon(false)`によって一般スレッドに設定できる
- `setDaemon()`はスレッドの開始前に呼び出す必要がある
- 開始済みのスレッドに対して呼び出すと`IllegalThreadStateException`が発生する
- `isDaemon()`によってデーモンスレッドか確認できる

**コード例**

```java
public class DaemonMain {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {

            while (true) {
                System.out.println("デーモンスレッド実行中");

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }
            }
        });

        thread.setDaemon(true);
        thread.start();

        System.out.println("mainスレッド終了");
    }
}
```

**実行時の特徴**

- 新しいスレッドはデーモンスレッドとして開始される
- `main`スレッドが終了すると、他に一般スレッドが存在しない場合、JVMは終了できる
- デーモンスレッドの`while`ループが無限に続く構造でも、JVMが終了する可能性がある
- スレッドの実行タイミングによっては、デーモンスレッドの出力が表示されない場合もある

### Runnableインターフェース

- `Runnable`は、スレッドで実行する処理を表現するインターフェース
- `Runnable`には、抽象メソッド`run()`が定義されている
- `Runnable`を実装したクラスに実行処理を記述できる
- `Runnable`自体が新しいスレッドを生成するわけではない
- `Runnable`を`Thread`に渡すことで、新しいスレッドで処理を実行できる
- `Runnable`を利用すると、処理内容とスレッドの実行管理を分離できる

### Runnableによるスレッドの生成

**コード例**

```java
public class RunnableMain {

    public static void main(String[] args) {

        Runnable task = new MyTask();

        Thread thread = new Thread(task);

        thread.start();
    }

    static class MyTask implements Runnable {

        @Override
        public void run() {
            System.out.println("Runnable実行");
        }
    }
}
```

**処理の流れ**

1. `MyTask`クラスが`Runnable`を実装する
2. `run()`メソッドに処理内容を定義する
3. `MyTask`のインスタンスを生成する
4. `Thread`のコンストラクタに`Runnable`を渡す
5. `start()`を呼び出して新しいスレッドを開始する
6. 新しいスレッドで`Runnable`の`run()`が実行される

### Threadの継承とRunnableの実装

| 比較項目 | Threadの継承 | Runnableの実装 |
| --- | --- | --- |
| 定義方法 | `extends Thread` | `implements Runnable` |
| 実行処理 | `run()`をオーバーライド | `run()`を実装 |
| スレッド開始 | `start()` | `Thread`に渡して`start()` |
| クラスの継承 | 他のクラスを継承できない | 他のクラスを継承できる |
| 処理とスレッド管理 | 一つのクラスにまとまりやすい | 分離しやすい |
| 再利用性 | 比較的制限がある | 処理を再利用しやすい |

**Runnableを利用する利点**

- Javaではクラスを複数継承できないため、`Thread`を継承すると他のクラスを継承できなくなる
- `Runnable`はインターフェースなので、他のクラスを継承しながら実装できる
- 処理内容とスレッドの生成・管理を分離できる
- 同じ`Runnable`インスタンスを複数のスレッドで実行することもできる
- ただし、複数のスレッドが同じオブジェクトの状態を共有する場合は注意が必要になる

### 匿名クラスによるRunnableの実装

- `Runnable`は匿名クラスとして実装できる
- 一度しか利用しない処理のために、個別のクラスを定義する必要がなくなる
- ただし、ラムダ式と比較すると記述量が多くなる

**コード例**

```java
Thread thread = new Thread(new Runnable() {

    @Override
    public void run() {
        System.out.println("匿名クラス実行");
    }
});

thread.start();
```

### ラムダ式によるRunnableの実装

- `Runnable`は抽象メソッドを一つ持つ関数型インターフェース
- 関数型インターフェースはラムダ式で実装できる
- ラムダ式を使用すると、匿名クラスよりも簡潔に処理を記述できる
- `Runnable`のラムダ式では、引数を受け取らず、戻り値も返さない
- ラムダ式を記述しただけではスレッドは開始されない

**コード例**

```java
Thread thread = new Thread(() -> {
    System.out.println("ラムダ式実行");
});

thread.start();
```

**Runnableの実装方法の比較**

```java
// 1. 実装クラスを使用
Runnable task1 = new MyTask();
Thread thread1 = new Thread(task1);

// 2. 匿名クラスを使用
Thread thread2 = new Thread(new Runnable() {
    @Override
    public void run() {
        System.out.println("匿名クラス");
    }
});

// 3. ラムダ式を使用
Thread thread3 = new Thread(() -> {
    System.out.println("ラムダ式");
});

thread1.start();
thread2.start();
thread3.start();
```

- どの方法でも`Runnable`の処理を新しいスレッドで実行できる
- 実装クラスは、処理が複雑な場合や再利用する場合に適している
- 匿名クラスは、クラス名を定義せずに処理を記述できる
- ラムダ式は、簡潔な処理を定義する場合に便利
- ラムダ式は、後に学習する関数型インターフェースやStream APIにも関係する

### 複数のスレッドの生成

- `Thread`インスタンスを複数生成することで、複数のスレッドを実行できる
- 各スレッドは独立して処理を進める
- スレッドごとに異なる処理を実行することもできる
- 同じ`Runnable`を複数のスレッドに渡すこともできる
- 複数のスレッドを開始した順序と、処理の終了順序は一致するとは限らない

**コード例**

```java
public class MultiThreadMain {

    public static void main(String[] args) {

        Runnable task = () -> {
            String name = Thread.currentThread().getName();

            for (int i = 1; i <= 3; i++) {
                System.out.println(name + " : " + i);
            }
        };

        Thread thread1 = new Thread(task, "thread-1");
        Thread thread2 = new Thread(task, "thread-2");

        thread1.start();
        thread2.start();

        System.out.println("main終了");
    }
}
```

**出力例**

```text
main終了
thread-1 : 1
thread-2 : 1
thread-1 : 2
thread-2 : 2
thread-2 : 3
thread-1 : 3
```

※ 実行結果の表示順序は毎回変化する可能性がある。

- `thread1`と`thread2`は同じ`Runnable`を参照している
- それぞれ別のスレッドとして`run()`を実行する
- この例のローカル変数`name`や`i`は、各スレッドの実行ごとに独立している
- 共有オブジェクトのフィールドなどを変更する場合は、データ競合に注意する必要がある

### ログユーティリティの作成

- マルチスレッド環境では、どのスレッドが処理を実行しているか確認することが重要になる
- スレッド名や実行時間をログに表示すると、処理の流れを把握しやすくなる
- ログ出力を共通メソッドにまとめることで、重複コードを減らせる
- スレッドの実行順序や処理時間を確認する際にも活用できる

**コード例**

```java
import java.time.LocalTime;

public class MyLogger {

    public static void log(Object message) {

        System.out.printf(
                "%s [%s] %s%n",
                LocalTime.now(),
                Thread.currentThread().getName(),
                message
        );
    }
}
```

**使用例**

```java
MyLogger.log("処理開始");
```

**出力例**

```text
17:30:15.123 [main] 処理開始
```

- `LocalTime.now()`で現在時刻を取得する
- `Thread.currentThread().getName()`でスレッド名を取得する
- `printf()`でログの形式を統一する
- スレッド名を表示することで、複数のスレッドの動作を区別できる
- 複数スレッドによるログの出力順序も、実行状況によって変化する

### スレッド生成時の注意事項

- `Thread`オブジェクトを生成するだけでは、新しいスレッドの処理は開始されない
- `start()`を呼び出して初めて新しいスレッドの実行が開始される
- `run()`を直接呼び出してもマルチスレッド処理にはならない
- 同じ`Thread`インスタンスに対して`start()`を複数回呼び出すことはできない
- スレッドの実行順序は保証されない
- `Thread.sleep()`を使用しても正確な実行順序は保証されない
- `setDaemon()`はスレッドの開始前に設定する必要がある
- デーモンスレッドは、処理完了前にJVMが終了する可能性がある
- 同じ`Runnable`を複数のスレッドで実行すると、参照するオブジェクトの状態が共有される場合がある
- スレッド数を無制限に増やすと、メモリ使用量やスケジューリングの負荷が増加する可能性がある

### スレッドの生成と実行のまとめ

- Javaでは`Thread`クラスを使用してスレッドを生成できる
- `run()`は実行する処理を定義するメソッド
- `start()`は新しいスレッドの実行を開始するメソッド
- `run()`を直接呼び出した場合は、新しいスレッドが生成されない
- `Thread.currentThread()`によって現在実行中のスレッドを取得できる
- `getName()`によってスレッド名を確認できる
- `Thread.sleep()`によって現在のスレッドを一定時間停止できる
- 一般スレッドがすべて終了すると、JVMはデーモンスレッドの終了を待たずに終了できる
- `Runnable`を利用すると、処理内容とスレッドの管理を分離できる
- `Runnable`は通常の実装クラス、匿名クラス、ラムダ式で実装できる
- 複数のスレッドは独立して処理を進めるが、実行順序は保証されない
- ログを活用することで、マルチスレッド環境の処理状況を確認しやすくなる
- 複数スレッドによる共有データの問題を理解することが、今後の同期処理やスレッド制御の学習につながる


## 3. スレッドの制御とライフサイクル1

### スレッドの基本情報

- `Thread`オブジェクトから、スレッドの名前・識別子・優先度・状態などを確認できる
- スレッドの情報を確認すると、マルチスレッド環境での動作を把握しやすくなる
- スレッドの名前はログやデバッグで特に役立つ
- スレッドIDは識別用の値であり、実行順序や優先度を意味しない

| メソッド | 説明 |
| --- | --- |
| `getName()` | スレッド名を取得する |
| `setName(String)` | スレッド名を設定する |
| `threadId()` | スレッドIDを取得する |
| `getPriority()` | スレッドの優先度を取得する |
| `getState()` | スレッドの現在の状態を取得する |
| `isAlive()` | スレッドが開始済みで、まだ終了していないか確認する |
| `isDaemon()` | デーモンスレッドか確認する |
| `Thread.currentThread()` | 現在実行中のスレッドを取得する |

※ `getId()`はJava 19以降非推奨。Java 19以降では`threadId()`を使用する。

**コード例**

```java
public class ThreadInfoMain {
    public static void main(String[] args) {
        Thread thread = Thread.currentThread();

        System.out.println("名前: " + thread.getName());
        System.out.println("ID: " + thread.threadId());
        System.out.println("優先度: " + thread.getPriority());
        System.out.println("状態: " + thread.getState());
        System.out.println("デーモン: " + thread.isDaemon());
    }
}
```

- `main`スレッドから実行すると、通常、スレッド名は`main`となる
- IDなどの具体的な値は実行環境によって異なる
- 優先度はスケジューラへのヒントであり、実行順序を保証しない

### スレッドのライフサイクル

- スレッドには、生成から終了までの間に複数の状態がある
- Javaでは`Thread.State`列挙型でスレッドの状態を表す
- `getState()`を使用すると、対象スレッドの状態を取得できる
- スレッドの状態は、実行状況や待機方法によって変化する
- 状態を取得した直後にも変化する可能性があるため、`getState()`は瞬間的な情報として扱う

| 状態 | 意味 | 主な場面 |
| --- | --- | --- |
| `NEW` | 生成済みで、まだ開始していない | `new Thread(...)`の直後 |
| `RUNNABLE` | JVM上で実行可能な状態 | `start()`後、実行中またはCPU待ち |
| `BLOCKED` | `synchronized`のモニターロック取得待ち | 他のスレッドがモニターを保持している |
| `WAITING` | 時間制限なしで他の処理を待機 | `join()`、`Object.wait()`など |
| `TIMED_WAITING` | 指定時間内で待機 | `sleep()`、時間指定の`join()`など |
| `TERMINATED` | 実行が終了した | `run()`の処理が終了した |

**重要なポイント**

- `RUNNABLE`は、CPUで実際に実行中の場合と、CPUの実行機会を待っている場合の両方を含む
- `BLOCKED`は単純な時間待機ではなく、モニターロックの取得待ちを表す
- `WAITING`と`TIMED_WAITING`は、時間制限の有無が主な違い
- `TERMINATED`になったスレッドを再び`start()`することはできない

### スレッドの状態遷移

スレッドは実行状況に応じて状態を変更する。

**基本的な流れ**

1. `Thread`インスタンスを生成する
2. `NEW`状態になる
3. `start()`を呼び出す
4. `RUNNABLE`状態になる
5. 待機やロック取得によって状態が変化する
6. 待機条件が解消されると`RUNNABLE`に戻る
7. `run()`の処理が終了すると`TERMINATED`になる

**状態遷移のイメージ**

```text
NEW
 |
 | start()
 v
RUNNABLE
 |      ^
 |      |
 |      +------------------+
 |                         |
 +--> BLOCKED -------------+
 |
 +--> WAITING -------------+
 |
 +--> TIMED_WAITING -------+
 |
 | run()終了
 v
TERMINATED
```

※ 実際の状態遷移は、使用するメソッドや同期処理の状況によって異なる。

### getState()による状態確認

- `getState()`は対象スレッドの現在の状態を返す
- スレッド開始前は`NEW`
- `sleep()`中は通常`TIMED_WAITING`
- スレッド終了後は`TERMINATED`
- スレッドの実行状態は常に変化するため、状態確認だけで処理の順序を制御するべきではない

**コード例**

```java
public class ThreadStateMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        }, "worker");

        System.out.println(thread.getState());

        thread.start();

        Thread.sleep(100);

        System.out.println(thread.getState());

        thread.join();

        System.out.println(thread.getState());
    }
}
```

**出力例**

```text
NEW
TIMED_WAITING
TERMINATED
```

- 最初はまだスレッドが開始されていないため`NEW`
- `start()`後にスレッドが`Thread.sleep()`を実行すると`TIMED_WAITING`
- `join()`で対象スレッドの終了を待つ
- 対象スレッド終了後は`TERMINATED`
- 実際の状態確認結果は実行タイミングによって変わる可能性がある

### スレッドの例外処理

- スレッド内で発生した例外は、そのスレッドの実行に影響する
- 新しいスレッドで発生した例外は、`start()`を呼び出したスレッドの`catch`には直接伝播しない
- スレッド内の例外を処理する場合は、そのスレッドの実行処理内で例外処理を行う
- 未処理の例外によって`run()`が終了すると、そのスレッドは終了する
- 他のスレッドは、例外が発生したスレッドとは独立して処理を継続できる

**コード例**

```java
public class ThreadExceptionMain {

    public static void main(String[] args) {

        try {
            Thread thread = new Thread(() -> {
                throw new RuntimeException("スレッド内の例外");
            });

            thread.start();

        } catch (RuntimeException e) {
            System.out.println("mainで例外処理");
        }
    }
}
```

**実行結果の特徴**

- 新しいスレッド内で`RuntimeException`が発生する
- `main`スレッドの`catch`では、その例外を直接捕捉できない
- 未処理の例外は通常、スレッドの例外ハンドラーによって報告される
- 例外が発生したスレッドは終了する

### UncaughtExceptionHandler

- `UncaughtExceptionHandler`は、スレッド内で捕捉されなかった例外を処理する仕組み
- `setUncaughtExceptionHandler()`で対象スレッドの例外ハンドラーを設定できる
- スレッド名や例外の内容をログに記録する場合などに使用する
- 例外ハンドラーを設定しても、例外が発生した処理をそのまま再開できるわけではない

**コード例**

```java
public class ExceptionHandlerMain {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {
            throw new RuntimeException("エラー発生");
        }, "worker");

        thread.setUncaughtExceptionHandler(
                (t, e) -> {
                    System.out.println(
                            "スレッド: " + t.getName()
                    );
                    System.out.println(
                            "例外: " + e.getMessage()
                    );
                }
        );

        thread.start();
    }
}
```

**出力例**

```text
スレッド: worker
例外: エラー発生
```

### Thread.sleep()

- `Thread.sleep()`は、現在実行中のスレッドを指定時間だけ一時停止するメソッド
- 指定時間はミリ秒単位で設定できる
- `sleep()`を呼び出したスレッドが`TIMED_WAITING`状態になる
- 指定時間が経過すると再び実行可能な状態になる
- 指定時間の経過後、すぐにCPUで処理が再開されるとは限らない
- 他のスレッドは基本的に独立して処理を継続する

**コード例**

```java
public class SleepMain {

    public static void main(String[] args) {

        Thread thread = new Thread(() -> {

            System.out.println("作業開始");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("作業再開");
        });

        thread.start();

        System.out.println("mainスレッド実行");
    }
}
```

**出力例**

```text
作業開始
mainスレッド実行
作業再開
```

※ 最初の2行の出力順序は変わる可能性がある。

**重要なポイント**

- `Thread.sleep(2000)`は約2秒間の待機を要求する
- 停止するのは`Thread.sleep()`を呼び出したスレッド
- `thread.sleep()`という書き方でも、対象の`thread`ではなく現在実行中のスレッドが停止する
- そのため、通常は`Thread.sleep()`というクラス名を使用した書き方が適切

### sleep()とスレッドの状態

`Thread.sleep()`を実行すると、スレッドは一定時間待機する。

```text
RUNNABLE
   |
   | Thread.sleep(1000)
   v
TIMED_WAITING
   |
   | 指定時間経過
   v
RUNNABLE
```

- `sleep()`はCPUの利用を一時的に停止する
- `sleep()`中でも、保持しているモニターロックは自動的に解放されない
- `sleep()`によってスレッドの実行順序が保証されるわけではない

### InterruptedException

- `InterruptedException`は、待機中のスレッドが割り込みを受けた場合などに発生するチェック例外
- `Thread.sleep()`や`join()`では、割り込みを受ける可能性がある
- チェック例外のため、`try-catch`または`throws`で対応する必要がある
- 割り込みはスレッドに対する協調的な中断要求として使用される
- 割り込みを受けた場合の具体的な動作は、プログラム側で適切に設計する必要がある

**コード例**

```java
try {
    Thread.sleep(1000);
} catch (InterruptedException e) {
    Thread.currentThread().interrupt();
    return;
}
```

- `sleep()`中に割り込みが発生すると、`InterruptedException`がスローされる
- この例では`interrupt()`で割り込み状態を復元する
- `return`によって現在の処理を終了する
- 割り込み状態を復元するかどうかは、処理の目的や上位の設計によって判断する

※ 割り込みの詳細については「スレッドの制御とライフサイクル2」で学習する。

### Thread.join()

- `join()`は、対象スレッドの終了を待つためのメソッド
- `join()`を呼び出したスレッドが待機する
- 対象スレッドは独立して処理を継続する
- 対象スレッドが終了すると、待機していたスレッドは処理を再開できる
- `join()`を使用すると、他のスレッドの処理結果が必要な場合に終了を待てる

**コード例**

```java
public class JoinMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {

            System.out.println("作業開始");

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("作業終了");
        });

        thread.start();

        System.out.println("main待機開始");

        thread.join();

        System.out.println("main処理再開");
    }
}
```

**出力例**

```text
main待機開始
作業開始
作業終了
main処理再開
```

※ 最初の2行の順序は変わる可能性があるが、`main処理再開`は対象スレッドが終了した後に出力される。

**処理の流れ**

1. `main`スレッドが新しいスレッドを生成する
2. `start()`によって新しいスレッドを開始する
3. 新しいスレッドで作業を実行する
4. `main`スレッドが`join()`を呼び出す
5. `main`スレッドは対象スレッドの終了まで待機する
6. 対象スレッドが終了する
7. `main`スレッドが処理を再開する

### join()とWAITING状態

- 引数なしの`join()`では、呼び出したスレッドが対象スレッドの終了を待機する
- 対象スレッドが実行中の場合、呼び出し元スレッドは通常`WAITING`状態になる
- 対象スレッドが終了すると、呼び出し元スレッドは再び実行可能になる
- `join()`は待機対象のスレッドを停止させるメソッドではない

**状態遷移のイメージ**

```text
mainスレッド               workerスレッド

RUNNABLE                   RUNNABLE
   |                          |
   | worker.join()            | 処理実行
   v                          |
WAITING                       |
   |                          |
   |                          | 処理終了
   |                          v
   |                       TERMINATED
   v
RUNNABLE
   |
   | 次の処理を実行
```

### join()が必要な理由

- マルチスレッド処理では、他のスレッドがいつ終了するか保証されない
- `start()`を呼び出しても、対象スレッドの処理完了を待たない
- 他のスレッドの計算結果が必要な場合、そのスレッドの終了を待つ必要がある
- `join()`を利用すると、対象スレッドの処理完了後に次の処理を実行できる

**コード例**

```java
public class JoinResultMain {

    private static int result = 0;

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {
            for (int i = 1; i <= 100; i++) {
                result += i;
            }
        });

        thread.start();

        thread.join();

        System.out.println("計算結果: " + result);
    }
}
```

**出力**

```text
計算結果: 5050
```

- `thread`が1から100までの合計を計算する
- `main`スレッドは`join()`によって計算完了を待機する
- 対象スレッドが終了した後で`result`を参照する
- `join()`による正常な終了待機にはメモリ可視性を保証する効果もある

※ 複数のスレッドが同じ変数を同時に更新する場合は、別途同期処理が必要になることがある。

### 複数スレッドでのjoin()

- 複数のスレッドを生成して並行処理を実行できる
- すべての処理が終了してから次の処理に進みたい場合、それぞれのスレッドに対して`join()`を呼び出す
- 各スレッドは並行して処理を実行できる
- `join()`を呼び出した順番にスレッドの処理が実行されるわけではない

**コード例**

```java
public class MultiJoinMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread1 = new Thread(() -> {
            System.out.println("作業1");
        });

        Thread thread2 = new Thread(() -> {
            System.out.println("作業2");
        });

        Thread thread3 = new Thread(() -> {
            System.out.println("作業3");
        });

        thread1.start();
        thread2.start();
        thread3.start();

        thread1.join();
        thread2.join();
        thread3.join();

        System.out.println("すべての作業終了");
    }
}
```

**出力例**

```text
作業2
作業1
作業3
すべての作業終了
```

- 3つのスレッドは並行して実行される
- `join()`によって各スレッドの終了を確認する
- すべてのスレッドが終了すると最後のメッセージが出力される
- 各作業の出力順序は保証されない

### join(long millis)

- `join(long millis)`は、対象スレッドの終了を指定時間内で待機するメソッド
- 引数なしの`join()`と異なり、待機時間を制限できる
- 対象スレッドが指定時間内に終了すると、その時点で待機を終了する
- 指定時間が経過すると、対象スレッドがまだ実行中でも待機を終了できる
- 時間指定の`join()`では、呼び出し元のスレッドは通常`TIMED_WAITING`状態になる
- タイムアウトしても対象スレッドが自動的に停止するわけではない

**コード例**

```java
public class TimedJoinMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {

            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
                return;
            }

            System.out.println("worker作業終了");
        });

        thread.start();

        System.out.println("main待機開始");

        thread.join(1000);

        System.out.println("main待機終了");
        System.out.println("worker生存: " + thread.isAlive());
    }
}
```

**出力例**

```text
main待機開始
main待機終了
worker生存: true
worker作業終了
```

- `worker`は約3秒間の処理を実行する
- `main`は`join(1000)`によって最大約1秒間待機する
- `worker`がまだ実行中でも、時間が経過すると`main`が処理を再開する
- `join(1000)`は対象スレッドを強制終了する処理ではない

### join()のタイムアウト確認

- `join(long millis)`は、対象スレッドが終了したかどうかを戻り値で通知しない
- `join()`の戻り値は`void`
- 待機終了後に`isAlive()`を使用すると、対象スレッドがまだ実行中か確認できる
- 対象スレッドが実行中の場合、必要に応じて追加の待機や別の処理を検討する

**コード例**

```java
thread.join(1000);

if (thread.isAlive()) {
    System.out.println("まだ処理中");
} else {
    System.out.println("処理完了");
}
```

### sleep()とjoin()の違い

| 比較項目 | `sleep()` | `join()` |
| --- | --- | --- |
| 主な目的 | 指定時間だけ待機する | 対象スレッドの終了を待つ |
| 呼び出し方法 | `Thread.sleep(1000)` | `thread.join()` |
| 待機するスレッド | 現在実行中のスレッド | `join()`を呼び出したスレッド |
| 待機対象 | 時間 | 特定のスレッドの終了 |
| 時間制限 | 指定時間が必要 | 指定可能 |
| 主な状態 | `TIMED_WAITING` | `WAITING`または`TIMED_WAITING` |
| 割り込み | `InterruptedException` | `InterruptedException` |
| 使用場面 | 一定時間の待機 | 他の処理の完了待ち |

**sleep()の例**

```java
Thread.sleep(1000);
```

- 現在のスレッドが約1秒待機する
- 他のスレッドの終了とは関係しない

**join()の例**

```java
thread.join();
```

- 現在のスレッドが`thread`の終了を待つ
- 対象スレッドが終了すると次の処理へ進む

### スレッド制御における注意事項

- `start()`はスレッドを開始するだけで、処理完了を待たない
- `sleep()`を使って実行順序を保証することはできない
- `join()`は対象スレッドの終了を待つために使用する
- `join()`を呼び出しているスレッドが待機し、対象スレッドは基本的に処理を継続する
- `join(long millis)`がタイムアウトしても、対象スレッドは自動的に終了しない
- `getState()`の結果は瞬間的な情報であるため、同期制御の代わりに使用しない
- `InterruptedException`は無視せず、処理方針に応じて適切に対応する
- 複数のスレッドが共有データを変更する場合は、データ競合に注意する
- `sleep()`は保持しているモニターロックを解放しない
- スレッドの終了待機と、共有データへの排他的アクセスは異なる概念

### スレッドの制御とライフサイクル1のまとめ

- スレッドの状態は`Thread.State`で表される
- 主な状態は`NEW`、`RUNNABLE`、`BLOCKED`、`WAITING`、`TIMED_WAITING`、`TERMINATED`の6種類
- `RUNNABLE`には実行中と実行可能な状態の両方が含まれる
- `getState()`で状態を取得できるが、状態は随時変化する
- 新しいスレッド内の例外は、`start()`側の`catch`に直接伝播しない
- `Thread.sleep()`は現在のスレッドを指定時間待機させる
- `join()`は指定した対象スレッドの終了を待つ
- `join(long millis)`は時間制限付きで対象スレッドの終了を待つ
- `sleep()`と`join()`は、いずれも呼び出したスレッドが待機する点が共通している
- `InterruptedException`は待機中の割り込みを扱うために重要な例外
- スレッドの状態と待機方法を理解することが、次の割り込み制御や同期処理の基礎になる

## 4. スレッドの制御とライフサイクル2

### スレッドの割り込み（Interrupt）

- 割り込み（Interrupt）は、スレッドに対して処理の中断を要求する仕組み
- `interrupt()`メソッドを使用して、対象スレッドに割り込みを通知できる
- 割り込みはスレッドを強制的に終了させる処理ではない
- 実際に処理を終了するかどうかは、割り込みを受けたスレッド側の実装によって決まる
- 割り込みを利用すると、実行中のスレッドや待機中のスレッドに終了要求を伝えられる
- マルチスレッド環境で、スレッドを安全に終了させるための重要な仕組み

**重要なポイント**

- `interrupt()`は終了命令ではなく、中断要求
- 割り込みを受けても、スレッドが必ず終了するわけではない
- スレッド側で割り込みを確認し、適切に処理する必要がある
- `sleep()`などで待機している場合は、`InterruptedException`が発生することがある

### interrupt()メソッド

- `interrupt()`は、対象スレッドに割り込みを通知するメソッド
- 通常実行中のスレッドに対して呼び出すと、割り込みフラグが設定される
- スレッドが割り込み可能な待機状態にある場合は、`InterruptedException`が発生することがある
- 割り込みによって、他のスレッドから処理終了を要求できる

**コード例**

```java
public class InterruptMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {

            while (true) {

                if (Thread.currentThread().isInterrupted()) {
                    System.out.println("割り込みを確認");
                    break;
                }

                System.out.println("作業中");
            }

            System.out.println("スレッド終了");
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

        thread.join();

        System.out.println("mainスレッド終了");
    }
}
```

**処理の流れ**

1. `main`スレッドが新しいスレッドを生成する
2. 新しいスレッドで繰り返し処理を実行する
3. `main`スレッドが約1秒間待機する
4. `thread.interrupt()`で割り込みを要求する
5. 新しいスレッドが`isInterrupted()`で割り込みを確認する
6. 割り込みが確認されるとループを終了する
7. `join()`でスレッドの終了を待機する
8. `main`スレッドが処理を終了する

※ この例は割り込み状態の確認を説明するためのものであり、実際の処理ではCPUを使い続けるループを避ける設計が必要になる。

### 割り込みフラグ（Interrupt Flag）

- Javaのスレッドには、割り込み状態を表すフラグが存在する
- 通常、割り込みが発生していない状態では`false`
- `interrupt()`を呼び出すと、割り込みフラグが`true`になる
- `isInterrupted()`を使用すると、割り込みフラグの状態を確認できる
- 割り込みフラグが`true`でも、スレッドが自動的に終了するわけではない
- `InterruptedException`の発生や`Thread.interrupted()`によって、フラグがクリアされる場合がある

**割り込みフラグのイメージ**

```text
通常状態
    |
    | interrupt()
    v
割り込みフラグ = true
    |
    | isInterrupted()
    v
割り込み状態を確認
    |
    | プログラム側で終了判断
    v
処理終了
```

※ `interrupt()`を呼び出しても、スレッドが終了しない場合がある。

### isInterrupted()メソッド

- `isInterrupted()`は、対象スレッドの割り込み状態を確認するメソッド
- 割り込みフラグが設定されている場合は`true`を返す
- 割り込みフラグが設定されていない場合は`false`を返す
- `isInterrupted()`は割り込みフラグを変更しない
- そのため、複数回呼び出しても、他の処理でフラグが変更されなければ同じ結果になる

**コード例**

```java
public class IsInterruptedMain {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();

        System.out.println(thread.isInterrupted());

        thread.interrupt();

        System.out.println(thread.isInterrupted());
        System.out.println(thread.isInterrupted());
    }
}
```

**出力例**

```text
false
true
true
```

**重要なポイント**

- `isInterrupted()`は割り込み状態を確認するだけ
- 割り込みフラグをクリアしない
- スレッドが終了要求を受けているか確認する場合に使用できる

### Thread.interrupted()メソッド

- `Thread.interrupted()`は、現在実行中のスレッドの割り込み状態を確認する静的メソッド
- 割り込み状態を確認すると同時に、割り込みフラグをクリアする
- 割り込みが発生している場合は`true`を返す
- 割り込みフラグをクリアした後は、通常`false`になる
- `isInterrupted()`とは異なり、割り込み状態が変更されるため注意が必要

**コード例**

```java
public class InterruptedMain {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();

        thread.interrupt();

        System.out.println(thread.isInterrupted());

        System.out.println(Thread.interrupted());

        System.out.println(thread.isInterrupted());

        System.out.println(Thread.interrupted());
    }
}
```

**出力例**

```text
true
true
false
false
```

**処理の流れ**

1. `interrupt()`によって割り込みフラグを`true`にする
2. `isInterrupted()`で割り込み状態を確認する
3. `Thread.interrupted()`で割り込み状態を取得する
4. この時点で割り込みフラグがクリアされる
5. 再度確認すると`false`になる

### isInterrupted()とThread.interrupted()の違い

| 比較項目 | `isInterrupted()` | `Thread.interrupted()` |
| --- | --- | --- |
| メソッドの種類 | インスタンスメソッド | 静的メソッド |
| 確認対象 | 指定したスレッド | 現在実行中のスレッド |
| 戻り値 | `boolean` | `boolean` |
| 割り込み状態の確認 | 可能 | 可能 |
| フラグのクリア | しない | する |
| 主な用途 | 割り込み状態の確認 | 割り込み状態の取得とクリア |

**注意事項**

- `Thread.interrupted()`は、現在実行中のスレッドに対して作用する
- `thread.interrupted()`と記述しても、`thread`の割り込み状態を確認するわけではない
- 割り込みフラグを保持したまま確認したい場合は`isInterrupted()`を使用する
- 割り込み状態を取得してクリアしたい場合は`Thread.interrupted()`を使用する

### sleep()中の割り込み

- `Thread.sleep()`を実行すると、現在のスレッドが指定時間待機する
- 待機中のスレッドに対して`interrupt()`を呼び出すと、`InterruptedException`が発生する
- `InterruptedException`が発生すると、スレッドの待機が解除される
- 例外が発生した際、割り込みフラグはクリアされる
- `catch`ブロック内で、その後の処理をどうするか決定する必要がある

**コード例**

```java
public class InterruptSleepMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread = new Thread(() -> {

            try {
                System.out.println("作業開始");

                Thread.sleep(5000);

                System.out.println("作業完了");

            } catch (InterruptedException e) {

                System.out.println("割り込み発生");

                System.out.println(
                        "割り込み状態: "
                        + Thread.currentThread().isInterrupted()
                );
            }

            System.out.println("スレッド終了");
        });

        thread.start();

        Thread.sleep(1000);

        thread.interrupt();

        thread.join();
    }
}
```

**出力例**

```text
作業開始
割り込み発生
割り込み状態: false
スレッド終了
```

**重要なポイント**

- `sleep(5000)`で約5秒間待機しようとする
- 約1秒後に`main`スレッドから割り込みが要求される
- 待機中のスレッドで`InterruptedException`が発生する
- `sleep()`の待機が解除される
- `InterruptedException`の発生に伴い、割り込みフラグがクリアされる
- `catch`ブロックで終了処理などを実行できる

※ `interrupt()`は、`sleep()`の指定時間が経過するまで待つ必要がない。

### InterruptedExceptionと割り込みフラグ

- `InterruptedException`は、割り込み可能な待機処理が中断された場合に発生する例外
- `Thread.sleep()`や`join()`などで発生する可能性がある
- `InterruptedException`が発生した際、割り込みフラグは通常クリアされる
- 割り込みが発生した事実を上位の処理に伝える必要がある場合は、フラグを復元する方法がある
- 割り込み状態を復元する場合は、`Thread.currentThread().interrupt()`を使用する

**割り込みフラグの復元**

```java
try {
    Thread.sleep(3000);

} catch (InterruptedException e) {

    Thread.currentThread().interrupt();

    System.out.println("割り込み状態を復元");

    return;
}
```

**処理の流れ**

1. `sleep()`中に割り込みが発生する
2. `InterruptedException`がスローされる
3. 割り込みフラグがクリアされる
4. `catch`ブロックが実行される
5. `interrupt()`で割り込み状態を復元する
6. `return`で処理を終了する

**注意事項**

- 例外を捕捉した後に、必ず割り込み状態を復元する必要があるわけではない
- 割り込みを上位の処理に伝える必要がある場合などに復元を検討する
- `InterruptedException`を上位に伝播できる場合は、そのまま`throws`で伝える方法もある
- 単に例外を無視すると、終了要求が適切に処理されない場合がある

### 割り込みを無視した場合の問題

- `interrupt()`を呼び出しても、対象スレッドが割り込みを処理しなければ実行を継続することがある
- `InterruptedException`を捕捉した後、何もせずに処理を継続すると終了要求が失われる場合がある
- 特に、`sleep()`を繰り返す処理では注意が必要になる

**問題のあるコード例**

```java
while (true) {

    try {
        Thread.sleep(1000);

    } catch (InterruptedException e) {
        // 割り込みを無視
    }

    System.out.println("作業継続");
}
```

- 割り込みが発生しても、ループが終了しない
- `InterruptedException`によって割り込みフラグがクリアされる
- 次の処理に進み、再びループが実行される

**改善例**

```java
while (true) {

    try {
        Thread.sleep(1000);

    } catch (InterruptedException e) {

        System.out.println("終了要求を受信");

        Thread.currentThread().interrupt();

        break;
    }

    System.out.println("作業継続");
}
```

- 割り込みを受けた場合はループを終了する
- 必要に応じて割り込み状態を復元する
- スレッドの終了要求を適切に処理できる

### プリンター例題1 - 開始

- プリンターの処理を別スレッドで実行するサンプルプログラム
- 印刷処理を繰り返し実行する仕組みについて学習
- `main`スレッドと印刷スレッドが独立して動作することを確認する
- 繰り返し処理を実行するスレッドに対して、終了制御が必要になることを理解する

**コード例（学習用）**

```java
public class PrinterMainV1 {

    public static void main(String[] args) {

        Thread printer = new Thread(() -> {

            for (int i = 1; i <= 10; i++) {

                System.out.println("印刷中: " + i);

                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    return;
                }
            }

        }, "printer");

        printer.start();

        System.out.println("mainスレッド終了");
    }
}
```

**重要なポイント**

- `printer`スレッドで印刷処理を実行する
- `main`スレッドとは独立して処理を進める
- `sleep()`で印刷処理の間隔を調整する
- `main`スレッドの処理が終了しても、一般スレッドである`printer`は処理を継続できる

### プリンター例題2 - 割り込みの導入

- 印刷処理を実行しているスレッドに対して、割り込みを要求する
- `interrupt()`を使用して印刷スレッドに終了要求を通知する
- `InterruptedException`によって、待機中の印刷処理を中断できることを確認する

**コード例（学習用）**

```java
public class PrinterMainV2 {

    public static void main(String[] args)
            throws InterruptedException {

        Thread printer = new Thread(() -> {

            while (true) {

                try {
                    System.out.println("印刷中");

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    System.out.println("印刷中断");

                    break;
                }
            }

            System.out.println("プリンター終了");

        }, "printer");

        printer.start();

        Thread.sleep(3000);

        printer.interrupt();

        printer.join();

        System.out.println("すべての処理終了");
    }
}
```

**処理の流れ**

1. プリンタースレッドを開始する
2. 印刷処理を繰り返し実行する
3. `main`スレッドが約3秒間待機する
4. `printer.interrupt()`で終了を要求する
5. `sleep()`中であれば`InterruptedException`が発生する
6. `catch`ブロックで印刷処理を終了する
7. `join()`で印刷スレッドの終了を待機する

### プリンター例題3 - 割り込み処理の改善

- 割り込みを利用した終了制御を改善する
- `isInterrupted()`で割り込み状態を確認する方法について学習
- `InterruptedException`による割り込みと、通常実行中の割り込みの違いを理解する
- スレッドが適切なタイミングで終了できるように処理を設計する

**コード例（学習用）**

```java
public class PrinterMainV3 {

    public static void main(String[] args)
            throws InterruptedException {

        Thread printer = new Thread(() -> {

            while (!Thread.currentThread().isInterrupted()) {

                try {
                    System.out.println("印刷中");

                    Thread.sleep(1000);

                } catch (InterruptedException e) {

                    Thread.currentThread().interrupt();

                    break;
                }
            }

            System.out.println("プリンター終了");

        }, "printer");

        printer.start();

        Thread.sleep(3000);

        printer.interrupt();

        printer.join();
    }
}
```

**改善ポイント**

- `while`の条件で割り込み状態を確認する
- 割り込みフラグが`true`の場合はループを継続しない
- `sleep()`中の割り込みは`catch`で処理する
- `InterruptedException`が発生した場合は、必要に応じて割り込み状態を復元する
- `break`によって終了処理へ進む

**重要なポイント**

- スレッドが通常実行中の場合、`isInterrupted()`による確認が有効
- `sleep()`中の割り込みは`InterruptedException`で通知される
- 割り込み状態を確認する処理と、例外を処理する仕組みの両方を理解することが重要

### Thread.yield() - CPUの実行機会を譲る

- `Thread.yield()`は、現在実行中のスレッドがCPUの実行機会を他のスレッドに譲るよう要求する静的メソッド
- CPUを使用する必要がないタイミングなどで、スケジューラに実行機会の調整を促すために使用できる
- `yield()`はスケジューラに対するヒントであり、必ず他のスレッドが実行されるわけではない
- `yield()`を呼び出しても、指定時間だけ待機するわけではない
- `yield()`によってスレッドの実行順序が保証されることはない
- `yield()`を呼び出したスレッドは、引き続き`RUNNABLE`状態である

**コード例**

```java
public class YieldMain {

    public static void main(String[] args)
            throws InterruptedException {

        Runnable task = () -> {

            for (int i = 1; i <= 5; i++) {

                System.out.println(
                        Thread.currentThread().getName()
                        + " : " + i
                );

                Thread.yield();
            }
        };

        Thread thread1 = new Thread(task, "thread-1");
        Thread thread2 = new Thread(task, "thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();
    }
}
```

**出力例**

```text
thread-1 : 1
thread-2 : 1
thread-1 : 2
thread-2 : 2
thread-1 : 3
thread-2 : 3
thread-1 : 4
thread-2 : 4
thread-1 : 5
thread-2 : 5
```

※ 実際の実行順序は保証されず、上記とは異なる順序になる場合がある。

**重要なポイント**

- `yield()`は他のスレッドへの実行機会の譲渡を提案する
- OSのスケジューラが必ず要求に従うわけではない
- `yield()`の直後に同じスレッドが再び実行されることもある
- `yield()`はスレッド間の処理順序を制御するための確実な手段ではない

### プリンター例題4 - yieldの導入

- プリンターの処理に`Thread.yield()`を導入する
- 複数のスレッドが実行される環境で、CPUの実行機会を譲る仕組みについて学習
- `yield()`を追加した場合でも、スレッドの実行順序は保証されないことを確認する

**コード例（学習用）**

```java
public class PrinterMainV4 {

    public static void main(String[] args)
            throws InterruptedException {

        Runnable printTask = () -> {

            for (int i = 1; i <= 10; i++) {

                if (Thread.currentThread().isInterrupted()) {
                    break;
                }

                System.out.println(
                        Thread.currentThread().getName()
                        + " 印刷中: " + i
                );

                Thread.yield();
            }

            System.out.println(
                    Thread.currentThread().getName()
                    + " 印刷終了"
            );
        };

        Thread printer1 = new Thread(printTask, "printer-1");
        Thread printer2 = new Thread(printTask, "printer-2");

        printer1.start();
        printer2.start();

        printer1.join();
        printer2.join();
    }
}
```

**重要なポイント**

- `printer1`と`printer2`は独立して印刷処理を進める
- `Thread.yield()`によって、他のスレッドに実行機会を譲るよう要求する
- 実際の処理順序はOSのスケジューリングによって変化する
- `yield()`は性能向上を保証するものではない
- `yield()`を利用しても、スレッド間の正確な実行順序は制御できない

### sleep()とyield()の違い

| 比較項目 | `Thread.sleep()` | `Thread.yield()` |
| --- | --- | --- |
| 主な目的 | 指定時間だけ待機する | CPUの実行機会を譲るよう要求する |
| 引数 | 待機時間 | なし |
| スレッド状態 | `TIMED_WAITING` | `RUNNABLE` |
| 待機時間 | 指定する | 指定しない |
| CPUの実行機会 | 待機中は通常CPUを使用しない | スケジューラの判断に依存 |
| 実行順序の保証 | なし | なし |
| 割り込み例外 | 発生する可能性がある | 発生しない |

**sleep()の例**

```java
Thread.sleep(1000);
```

- 現在のスレッドが約1秒間待機する
- 指定時間が経過するまでは、通常処理を再開しない
- 割り込みによって待機が解除される場合がある

**yield()の例**

```java
Thread.yield();
```

- 現在のスレッドがCPUの実行機会を譲るよう要求する
- 必ず他のスレッドに切り替わるとは限らない
- 呼び出し直後に同じスレッドの処理が継続する場合もある

### interrupt()とsleep()とjoin()とyield()の比較

| メソッド | 主な目的 | 作用するスレッド | 特徴 |
| --- | --- | --- | --- |
| `interrupt()` | 割り込み要求 | 対象スレッド | 強制終了ではない |
| `sleep()` | 時間待機 | 現在のスレッド | 指定時間待機する |
| `join()` | 終了待機 | 呼び出し元スレッド | 対象スレッドの終了を待つ |
| `yield()` | CPU実行機会の譲渡要求 | 現在のスレッド | 実際の切り替えは保証されない |

**使用場面の例**

- バックグラウンド処理に終了を要求したい場合は`interrupt()`
- 一定時間だけ処理を待機させたい場合は`sleep()`
- 他のスレッドの処理完了を待ちたい場合は`join()`
- CPU実行機会を他のスレッドに譲るヒントを与えたい場合は`yield()`

### スレッド制御における注意事項

- `interrupt()`はスレッドを強制終了するメソッドではない
- 割り込みを受けたスレッドが、適切に処理する必要がある
- `isInterrupted()`は割り込みフラグをクリアしない
- `Thread.interrupted()`は現在のスレッドの割り込みフラグをクリアする
- `InterruptedException`が発生すると、通常は割り込みフラグがクリアされる
- 割り込みを無視すると、スレッドが終了要求に応答しなくなる可能性がある
- 割り込みを利用して終了制御を実装する場合は、ループ条件や例外処理を適切に設計する
- `yield()`はCPUの実行機会を譲るヒントであり、処理順序を保証しない
- `sleep()`と`yield()`は、どちらもスレッドの実行順序を保証するための方法ではない
- `join()`は対象スレッドの終了を待つために使用する
- スレッドの終了要求と、スレッドの終了待機は異なる概念
- マルチスレッド環境では、スレッドの状態と実行タイミングが随時変化することを意識する

### スレッドの制御とライフサイクル2のまとめ

- 割り込み（Interrupt）は、スレッドに処理の中断を要求する仕組み
- `interrupt()`はスレッドを強制終了させるものではない
- 割り込みフラグによって、スレッドの割り込み状態を管理する
- `isInterrupted()`は対象スレッドの割り込み状態を確認し、フラグを変更しない
- `Thread.interrupted()`は現在のスレッドの割り込み状態を確認し、フラグをクリアする
- `sleep()`などの待機中に割り込みが発生すると、`InterruptedException`が発生する
- `InterruptedException`の発生時には、通常、割り込みフラグがクリアされる
- 必要に応じて`Thread.currentThread().interrupt()`で割り込み状態を復元できる
- 割り込み要求を適切に処理することで、スレッドを安全に終了させる設計ができる
- プリンターの例題を通して、割り込みを利用した処理終了の基本的な実装方法について学習
- `Thread.yield()`は、現在のスレッドがCPUの実行機会を他のスレッドに譲るよう要求するメソッド
- `yield()`はスケジューラへのヒントであり、実際の実行順序を保証しない
- `sleep()`は時間待機、`join()`は終了待機、`interrupt()`は中断要求、`yield()`はCPU実行機会の譲渡要求に使用する
- スレッドの割り込みと実行制御を理解することで、今後学習するメモリの可視性や同期処理の基礎につながる


## 5. メモリの可視性

### メモリの可視性（Memory Visibility）

- メモリの可視性とは、あるスレッドが変更した共有変数の値を、他のスレッドが正しく観測できること
- マルチスレッド環境では、複数のスレッドが同じオブジェクトや変数を共有する場合がある
- 一つのスレッドが共有変数の値を変更しても、他のスレッドがその変更を必ず確認できるとは限らない
- 適切な同期処理が存在しない場合、別のスレッドが以前の値を参照し続ける可能性がある
- このような問題をメモリの可視性問題という
- メモリの可視性は、マルチスレッドプログラミングにおいて重要な概念の一つ

**基本的なイメージ**

```text
共有変数 running = true

スレッドA（main）
    |
    | running = false
    v
共有変数の値を変更

スレッドB（worker）
    |
    | runningを繰り返し確認
    v
古い値 true を参照し続ける可能性
```

**重要なポイント**

- 共有変数を変更しただけでは、他のスレッドへの可視性が保証されるとは限らない
- この問題は、CPUキャッシュやコンパイラの最適化などと関係している
- Javaでは、`volatile`や同期処理などによって可視性を確保できる

### メモリの可視性が問題になる理由

- CPUは処理を高速化するためにキャッシュなどを利用する
- JVMのJITコンパイラも、プログラムの処理を最適化する場合がある
- 適切な同期がない場合、共有変数の読み込みが最適化され、変更が観測されないことがある
- そのため、複数のスレッドが同じ変数を参照していても、期待したタイミングで値の変更が確認できるとは限らない
- メモリの可視性問題は、単純なプログラムでも発生する可能性がある

**注意事項**

メモリの可視性問題は、CPUキャッシュだけが原因ではない。

JITコンパイラによる最適化や、Java Memory Modelで定められたメモリ操作のルールも関係している。

### volatileを使用しない場合

`volatile`を使用せず、複数のスレッドで終了フラグを共有する場合を考える。

**コード例**

```java
public class VolatileMainV1 {

    private static boolean running = true;

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {

            while (running) {
                // 終了フラグを確認
            }

            System.out.println("worker終了");
        });

        worker.start();

        Thread.sleep(1000);

        running = false;

        System.out.println("main終了要求");
    }
}
```

**期待する処理の流れ**

1. `running`の初期値は`true`
2. `worker`スレッドが処理を開始する
3. `worker`は`running`が`true`の間、ループを繰り返す
4. `main`スレッドが約1秒間待機する
5. `main`スレッドが`running`を`false`に変更する
6. `worker`が変更を確認してループを終了する

**実際に発生する可能性がある問題**

- `running`には`volatile`が付いていない
- `worker`が変更前の値を参照し続ける可能性がある
- `main`が`false`に変更しても、`worker`が終了しない場合がある
- 必ず終了しないという意味ではなく、終了することもある
- 重要なのは、適切な同期がないため可視性が保証されないということ

※ このコードは可視性問題を説明するための例題であり、実行環境によっては終了しない可能性がある。

### volatileキーワード

- `volatile`は、フィールドのメモリ可視性を確保するためのJavaのキーワード
- 複数のスレッドが同じ変数を参照する場合に使用できる
- `volatile`変数の読み書きには、Java Memory Modelによる特別なルールが適用される
- あるスレッドによる変更を、別のスレッドが適切に観測できるようにする
- `volatile`には、メモリ操作の順序に関する保証もある
- ただし、`volatile`は排他制御を実行する仕組みではない

**宣言方法**

```java
private static volatile boolean running = true;
```

**キーワードの意味**

| キーワード | 役割 |
| --- | --- |
| `private` | アクセス範囲を制限する |
| `static` | クラスに属するフィールドにする |
| `volatile` | 可視性と順序性を保証する |
| `boolean` | 真偽値を扱う |

### volatileによる可視性の確保

先ほどのプログラムを`volatile`で改善する。

**コード例**

```java
public class VolatileMainV2 {

    private static volatile boolean running = true;

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {

            while (running) {
                // 終了フラグを確認
            }

            System.out.println("worker終了");
        });

        worker.start();

        Thread.sleep(1000);

        running = false;

        System.out.println("main終了要求");

        worker.join();

        System.out.println("すべての処理終了");
    }
}
```

**出力例**

```text
main終了要求
worker終了
すべての処理終了
```

※ 最初の2行の出力順序は実行タイミングによって変わる可能性がある。

**処理の流れ**

1. `running`を`volatile`として宣言する
2. `worker`スレッドがループ処理を開始する
3. `main`スレッドが約1秒間待機する
4. `main`スレッドが`running = false`を実行する
5. `worker`スレッドが変更された値を観測する
6. ループを終了して`worker終了`を出力する
7. `main`スレッドが`join()`から復帰する
8. 最後に`すべての処理終了`を出力する

**重要なポイント**

- `volatile`によって共有変数の可視性を確保する
- `volatile`を付けるだけで、複雑なロック処理を記述する必要がない場合もある
- `join()`はスレッドの終了を待機するために使用している
- `volatile`は終了要求の可視性を確保し、`join()`は終了完了を待つ
- `volatile`自体がスレッドを終了させるわけではない

※ この例は学習用のビジーウェイトであり、実際のアプリケーションではCPUを消費し続ける待機方法を避けることが望ましい。

### volatileを使用しない場合と使用した場合

| 比較項目 | volatileなし | volatileあり |
| --- | --- | --- |
| メモリの可視性 | 保証されない | 保証される |
| 共有変数の変更 | 他スレッドが観測できない場合がある | JMMの規則に従って観測できる |
| メモリ操作の順序性 | 必要な順序が保証されない場合がある | 所定の順序性が保証される |
| 排他制御 | なし | なし |
| 複合操作の原子性 | 保証されない | 保証されない |
| 主な用途 | スレッド間共有を伴わない一般的な変数など | 終了フラグや状態通知など |

### volatileと原子性（Atomicity）

`volatile`を使用しても、すべての操作が安全になるわけではない。

特に重要なのが、原子性（Atomicity）との違いである。

**原子性とは**

- ある操作が、他のスレッドから途中の状態を観測されることなく、一つのまとまった操作として実行される性質
- 複数の処理に分かれる操作では、途中で他のスレッドが割り込むように実行される場合がある
- `volatile`は複合操作全体の原子性を保証しない

**コード例**

```java
private static volatile int count = 0;

count++;
```

一見すると`count++`は一つの操作に見える。

しかし、概念的には次のような処理に分かれる。

```text
1. countの値を読み込む
2. 読み込んだ値に1を加算する
3. 加算結果をcountに書き込む
```

**複数スレッドで発生する問題**

```text
初期値 count = 0

スレッドA                スレッドB
    |                        |
    | countを読む → 0        | countを読む → 0
    |                        |
    | 0 + 1 = 1              | 0 + 1 = 1
    |                        |
    | count = 1              | count = 1
    |                        |
    v                        v

最終結果 count = 1
```

本来は2回加算したため、結果は`2`になることを期待する。

しかし、両方のスレッドが同じ値を読み込んで更新すると、結果が`1`になる場合がある。

このような問題を更新の消失（Lost Update）という。

### volatileによる加算処理の問題

**コード例**

```java
public class VolatileCountMain {

    private static volatile int count = 0;

    public static void main(String[] args)
            throws InterruptedException {

        Runnable task = () -> {

            for (int i = 0; i < 100000; i++) {
                count++;
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }
}
```

**期待する結果**

```text
count = 200000
```

**実際の結果**

- `200000`になる場合もある
- `200000`より小さくなる場合もある
- 実行するたびに結果が変化する可能性がある

**問題の原因**

- `count++`は読み込み・加算・書き込みから構成される
- 複数のスレッドが同じ値を読み込む可能性がある
- `volatile`はこの複合操作を不可分にはしない
- `join()`でスレッドの終了を待っても、途中で発生した更新の消失は解決できない

**改善するための方法**

- `synchronized`による排他制御
- `Lock`による排他制御
- `AtomicInteger`などによるアトミック操作

※ これらの具体的な実装方法は、後の同期処理とCASの章で学習する。

### 可視性と原子性の違い

| 比較項目 | 可視性（Visibility） | 原子性（Atomicity） |
| --- | --- | --- |
| 主な目的 | 変更された値を他のスレッドから観測できるようにする | 操作を不可分な単位として扱う |
| 主な問題 | 古い値を読み続ける | 複数の更新が競合する |
| 代表的な例 | 終了フラグの変更 | `count++` |
| volatile | 保証できる | 複合操作は保証できない |
| synchronized | 保証できる | 保護した処理の排他制御ができる |

**重要なポイント**

- 可視性と原子性は異なる概念
- `volatile`は可視性を確保するために利用できる
- `volatile`だけでは複数スレッドによる加算処理を安全にできない
- 共有変数を更新する場合は、処理内容に応じて適切な同期方法を選択する必要がある

### Java Memory Model（JMM）

Java Memory Model（JMM）は、Javaにおけるスレッド間のメモリ操作や、その可視性・順序性などを定めた仕組みである。

**基本概念**

- Javaプログラムがマルチスレッド環境で動作する際のメモリ操作のルールを定義する
- スレッド間で共有する変数の読み書きについて定めている
- メモリの可視性や操作の順序性に関係する
- JVMやCPUによる最適化が行われても、Javaプログラムが従うべき規則を定めている
- `volatile`や`synchronized`などの動作を理解するために重要な概念

### メインメモリと作業メモリのイメージ

JMMを理解するために、メインメモリとスレッドごとの作業メモリという考え方を利用できる。

```text
            メインメモリ
        +----------------+
        | 共有変数       |
        | running = true |
        +----------------+
           ^          ^
           |          |
           v          v
    +-----------+  +-----------+
    | スレッドA |  | スレッドB |
    | 作業領域  |  | 作業領域  |
    +-----------+  +-----------+
```

**重要なポイント**

- JMMは実際のCPUキャッシュ構造をそのまま表しているわけではない
- メインメモリと作業メモリは、JMMを理解するための概念的なモデル
- 実際の動作にはCPUキャッシュ、レジスタ、コンパイラ最適化などが関係する
- 重要なのは、共有データに対するスレッド間の可視性がどのように保証されるかという点

### Happens-Before関係

Happens-Beforeは、Java Memory Modelにおけるメモリ操作の順序と可視性を表す重要な関係である。

**基本概念**

- ある処理の結果が、別の処理から適切に観測できることを保証するための関係
- Happens-Before関係が成立すると、先行する処理のメモリ上の効果が後続の処理に対して可視になる
- 単純な実行時刻の前後関係だけを意味するものではない
- スレッド間のメモリ可視性を理解するために重要な仕組み

**代表的なHappens-Before関係**

| 操作 | 保証される関係 |
| --- | --- |
| 同一スレッド内の処理 | プログラム順序に従った関係 |
| `Thread.start()` | start前の処理と開始されたスレッドの処理 |
| `Thread.join()` | 対象スレッドの処理と正常な終了待機後の処理 |
| `volatile` | 同じ変数への書き込みと、それ以降の読み込み |
| `synchronized` | 同じモニターのロック解放と、その後のロック取得 |

※ `join()`は対象スレッドの終了を確認できた場合の保証であり、時間指定の`join()`がタイムアウトしただけでは同じ保証は得られない。

### volatileとHappens-Before

`volatile`変数への書き込みと、その後の同じ変数の読み込みにはHappens-Before関係が成立する。

**コード例**

```java
public class HappensBeforeMain {

    private static int data = 0;
    private static volatile boolean ready = false;

    public static void main(String[] args)
            throws InterruptedException {

        Thread reader = new Thread(() -> {

            while (!ready) {
                Thread.onSpinWait();
            }

            System.out.println("data = " + data);
        });

        Thread writer = new Thread(() -> {

            data = 100;

            ready = true;
        });

        reader.start();
        writer.start();

        reader.join();
        writer.join();
    }
}
```

**出力**

```text
data = 100
```

**処理の流れ**

1. `data`には`volatile`を付けていない
2. `ready`には`volatile`を付けている
3. `writer`スレッドが`data = 100`を実行する
4. 続いて`ready = true`を実行する
5. `reader`スレッドが`ready`の変更を確認する
6. `reader`が`data`を読み込む
7. `data = 100`が表示される

**なぜdataにもvolatileが必要ないのか**

- `writer`では`data = 100`の後に`ready = true`が実行される
- `ready`は`volatile`変数である
- `reader`が`ready = true`を観測した場合、Happens-Before関係によって、それ以前の`data`への書き込みも可視になる
- そのため、この例では`data`自体に`volatile`を付けなくても値を正しく観測できる

※ この保証は、例題のように書き込み順序と読み込み順序が適切であり、別の競合する更新がないことを前提としている。

※ `Thread.onSpinWait()`はスピン待機中であることを示すヒントであり、それ自体に同期や可視性を保証する効果はない。

### メモリ操作の順序性（Ordering）

- CPUやJITコンパイラは、プログラムの処理を効率化するために命令の順序を調整する場合がある
- このような最適化によって、他のスレッドから観測される処理の順序に影響が出る可能性がある
- Java Memory Modelは、どのような順序が保証されるかを定めている
- `volatile`の読み書きには、通常のフィールドとは異なる順序性の保証がある
- `volatile`は、必要なメモリ操作の順序を保つためにも利用される

**重要なポイント**

- `volatile`は単純に最新の値を読み込むためだけのキーワードではない
- メモリの可視性と順序性の両方に関係する
- ただし、すべての操作の順序を固定したり、複合操作を原子的にしたりするわけではない

### volatileとsleep()の違い

`Thread.sleep()`を使用しても、メモリの可視性は保証されない。

**理由**

- `sleep()`は現在のスレッドを一定時間待機させるためのメソッド
- 待機時間の経過によって、他のスレッドによる変更が必ず観測できるようになるわけではない
- `sleep()`自体は共有変数に対するHappens-Before関係を作らない
- `yield()`についても同様に、メモリの可視性を保証するものではない

**比較**

| メソッド・キーワード | 主な目的 |
| --- | --- |
| `sleep()` | 指定時間の待機 |
| `join()` | 対象スレッドの終了待機 |
| `interrupt()` | 対象スレッドへの割り込み要求 |
| `yield()` | CPU実行機会の譲渡要求 |
| `volatile` | 共有変数の可視性と順序性の確保 |

### volatileの主な使用場面

**1. 終了フラグ**

```java
private volatile boolean running = true;
```

- 別のスレッドから終了要求を通知する
- 単純な状態変更を他のスレッドから確認する場合に適している

**2. 状態通知フラグ**

```java
private volatile boolean completed = false;
```

- 処理完了などの状態を共有する
- 複雑な更新処理が必要ない場合に利用できる

**3. 共有状態の参照**

```java
private volatile int status = 0;
```

- 一つの値を読み書きして状態を伝える
- 複数スレッドによる複合的な更新が必要な場合は、別の同期方法を検討する

### volatileを使用する際の注意事項

- `volatile`はすべてのマルチスレッド問題を解決するものではない
- `volatile`によって可視性と順序性を確保できる
- `count++`などの複合操作の原子性は保証されない
- `volatile`自体は排他制御を行わない
- 複数の共有変数にまたがる整合性を保つ場合は、追加の同期処理が必要になることがある
- `sleep()`や`yield()`でメモリ可視性の問題を解決しようとしない
- `join()`で処理完了を待つことと、共有データを安全に更新することは別の問題
- 複数スレッドが同じデータを更新する場合は、原子性と可視性の両方を考慮する
- 必要に応じて`synchronized`や`Lock`、アトミッククラスなどを使用する

### メモリの可視性のまとめ

- メモリの可視性とは、あるスレッドによる共有変数の変更を他のスレッドが正しく観測できること
- 適切な同期がない場合、別のスレッドが古い値を参照し続ける可能性がある
- CPUキャッシュやJITコンパイラによる最適化などが可視性の問題に関係する
- `volatile`は共有変数に対する可視性と順序性を保証する
- `volatile`を利用すると、単純な終了フラグなどをスレッド間で共有できる
- `volatile`は排他制御を行わない
- `count++`などの複合操作は、`volatile`を付けても原子的にはならない
- 可視性と原子性は異なる概念である
- Java Memory Model（JMM）は、Javaにおけるスレッド間のメモリ操作に関するルールを定めている
- Happens-Before関係によって、スレッド間のメモリ可視性と必要な順序が保証される
- `volatile`変数への書き込みは、その後の同じ変数への読み込みに対してHappens-Before関係を成立させる
- `sleep()`や`yield()`だけでは共有変数の可視性を保証できない
- `join()`による正常な終了待機には、対象スレッドの処理結果に対する可視性の保証がある
- 複数スレッドによる安全な更新処理を実現するには、`synchronized`や`Lock`などの同期処理について学ぶ必要がある
- メモリの可視性とJMMを理解することは、Javaにおけるスレッドセーフなプログラムを作成するための重要な基礎となる


## 6. 同期処理 - synchronized

### 同期処理（Synchronization）

- 同期処理は、複数のスレッドが共有データにアクセスする際に、データの整合性を維持するための仕組み
- マルチスレッド環境では、複数のスレッドが同じオブジェクトや変数を同時に操作する場合がある
- 共有データに対する読み込みや更新が競合すると、予期しない結果が発生する可能性がある
- このような問題を防ぐために、必要な処理を適切に同期する
- Javaでは、`synchronized`キーワードを使用して同期処理を実装できる
- `synchronized`を使用すると、同じモニターロックを使用するスレッド間で排他制御を実現できる
- 同期処理は、スレッドセーフ（Thread Safe）なプログラムを作成するための重要な仕組み

**重要なポイント**

- 複数のスレッドが存在するだけでは、必ず同期処理が必要になるわけではない
- 同じデータを共有しているかどうかが重要
- 特に複数のスレッドが共有データを更新する場合は注意が必要
- 同期処理によって、共有データの不正な更新を防ぐことができる

### 銀行口座の出金例題 - 開始

銀行口座からお金を引き出す処理を考える。

口座残高が1000円の場合、800円を引き出す処理を2つのスレッドから同時に実行する。

**同期処理を使用しないコード例**

```java
public class BankMain {

    public static void main(String[] args)
            throws InterruptedException {

        BankAccount account = new BankAccount(1000);

        Thread thread1 = new Thread(() -> {
            account.withdraw(800);
        }, "thread-1");

        Thread thread2 = new Thread(() -> {
            account.withdraw(800);
        }, "thread-2");

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println(
                "最終残高: " + account.getBalance()
        );
    }

    static class BankAccount {

        private int balance;

        public BankAccount(int balance) {
            this.balance = balance;
        }

        public void withdraw(int amount) {

            if (balance >= amount) {

                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    Thread.currentThread().interrupt();
                    return;
                }

                balance -= amount;
            }
        }

        public int getBalance() {
            return balance;
        }
    }
}
```

**期待する結果**

```text
最終残高: 200
```

口座残高が1000円の場合、800円の出金は1回だけ成功することを期待する。

**実際に発生する可能性がある結果**

```text
最終残高: -600
```

※ スレッドの実行タイミングによって結果は変化する。

**問題の原因**

- `thread1`と`thread2`が同じ`BankAccount`インスタンスを共有している
- 両方のスレッドが同じ`balance`を参照する
- `balance >= amount`による残高確認と、実際の出金処理が一つの不可分な操作になっていない
- 2つのスレッドが同時に残高確認を通過する可能性がある
- その結果、残高不足にもかかわらず出金が実行される場合がある

### 同時実行問題（Concurrency Problem）

- 同時実行問題は、複数のスレッドが共有データを同時に操作することで発生する問題
- 個別のスレッドでは正しく動作する処理でも、複数のスレッドから実行すると問題が発生する場合がある
- スレッドの実行順序は保証されないため、実行するたびに結果が変化する可能性がある
- 共有データの読み込みと更新が競合すると、データの整合性が失われる場合がある

**出金処理で発生する問題のイメージ**

```text
初期残高 balance = 1000

thread-1                       thread-2
   |                              |
   | 残高確認                     |
   | 1000 >= 800 → true           |
   |                              |
   |                              | 残高確認
   |                              | 1000 >= 800 → true
   |                              |
   | balance = 1000 - 800         |
   | balance = 200                |
   |                              |
   |                              | balance = 200 - 800
   |                              | balance = -600
   |                              |
   v                              v

最終残高 = -600
```

**重要なポイント**

- 両方のスレッドが出金可能と判断してしまうことが問題
- 残高確認と残高更新の間に、他のスレッドが処理を実行する可能性がある
- `if`文による条件確認だけでは、共有データの安全性を保証できない
- 残高確認から残高更新までを、一つのまとまった処理として保護する必要がある

### 競合状態（Race Condition）

- 競合状態は、複数のスレッドの実行タイミングや順序によって、処理結果が変化する状態
- 共有データに対する複数の操作が適切に同期されていない場合に発生する
- スレッドがどの順序で実行されるかによって、不正な結果になる場合がある
- 同じプログラムでも、実行するたびに異なる結果になる可能性がある
- 競合状態を防ぐには、共有データへのアクセス方法を適切に制御する必要がある

**代表的な例**

```java
private int count = 0;

public void increment() {
    count++;
}
```

`count++`は概念的に次の操作に分かれる。

```text
1. countの値を読み込む
2. 読み込んだ値に1を加算する
3. 加算結果をcountに書き込む
```

複数のスレッドが同じ値を読み込むと、更新が失われる場合がある。

**重要なポイント**

- `count++`は一つのコードに見えても、複数の操作から構成されている
- `volatile`を付けても、この複合操作の原子性は保証されない
- 競合状態を防止するには、必要に応じて排他制御を使用する

### 臨界領域（Critical Section）

臨界領域は、複数のスレッドが同時に実行すると問題が発生する可能性があるコード領域。

- 共有データの整合性を維持するために、保護する必要がある処理範囲
- 複数のスレッドが同時に実行すると、競合状態が発生する可能性がある
- 臨界領域では、必要に応じて一度に一つのスレッドだけが処理を実行できるようにする
- Javaでは、`synchronized`などを利用して臨界領域を保護できる
- 臨界領域は、共有データの読み込みと更新を含む場合がある

**出金処理の臨界領域**

```java
if (balance >= amount) {
    balance -= amount;
}
```

この処理では、次の2つの操作をまとめて保護する必要がある。

1. 出金可能な残高があるか確認する
2. 出金可能であれば残高を更新する

**重要なポイント**

- `balance -= amount`だけを保護しても不十分
- 残高確認と残高更新の両方を同じ臨界領域に含める必要がある
- 臨界領域を適切に設定することで、共有データの整合性を保てる

### 排他制御（Mutual Exclusion）

- 排他制御は、特定の処理領域に同時にアクセスできるスレッドを制限する仕組み
- 一つのスレッドが保護対象の処理を実行している間、他のスレッドが同じロックを必要とする処理に入れないようにする
- 排他制御によって、共有データに対する操作の競合を防止できる
- Javaの`synchronized`は、モニターロックを利用して排他制御を実現する

**排他制御のイメージ**

```text
        共有データ
      balance = 1000
            |
       モニターロック
            |
      +-----------+
      |           |
   thread-1    thread-2
      |           |
   ロック取得   ロック待機
      |
   残高確認
      |
   残高更新
      |
   ロック解放
                  |
               ロック取得
                  |
               残高確認
                  |
               出金不可
                  |
               ロック解放
```

- ロックを取得したスレッドだけが保護された処理を実行できる
- 同じロックを取得しようとする他のスレッドは待機する
- ロックが解放されると、待機していたスレッドがロックを取得できる可能性がある

### synchronizedキーワード

- `synchronized`は、Javaで同期処理を実装するためのキーワード
- メソッドまたはコードブロックに対して使用できる
- 指定したオブジェクトのモニターロックを利用して排他制御を行う
- 同じモニターロックを使用する処理は、一度に一つのスレッドだけが実行できる
- `synchronized`は排他制御だけでなく、メモリの可視性にも関係する
- ロックの取得と解放によって、Java Memory Modelに基づくメモリ可視性が保証される

**基本的な使用方法**

```java
public synchronized void method() {
    // 同期処理
}
```

または

```java
synchronized (lock) {
    // 同期処理
}
```

**重要なポイント**

- `synchronized`はスレッドそのものをロックする仕組みではない
- 特定のオブジェクトに関連付けられたモニターロックを取得する
- 同じロックを使用するスレッド同士で排他制御が行われる
- 異なるロックを使用する場合は、互いに排他制御されない

### モニターロック（Monitor Lock）

- Javaの各オブジェクトには、モニターロックに関する仕組みが存在する
- `synchronized`は、このモニターロックを使用して同期処理を実現する
- 一つのモニターロックを同時に保持できるスレッドは一つだけ
- すでにロックが取得されている場合、他のスレッドはロックが解放されるまで待機する
- 同じロックを取得しようとして待機しているスレッドは、`BLOCKED`状態になる
- ロックが解放されると、待機中のスレッドがロックを取得できるようになる

**モニターロックの処理の流れ**

```text
thread-1

1. synchronizedに到達
2. モニターロックを取得
3. 臨界領域を実行
4. 処理終了
5. モニターロックを解放


thread-2

1. synchronizedに到達
2. モニターロックの取得を試みる
3. thread-1が保持中のためBLOCKED
4. thread-1がロックを解放
5. モニターロックを取得
6. 臨界領域を実行
7. モニターロックを解放
```

**重要なポイント**

- モニターロックは、オブジェクトを基準として管理される
- 同じオブジェクトの同じモニターロックを使用することが重要
- ロックの取得順序は保証されない
- `synchronized`の処理が正常終了した場合、ロックは自動的に解放される
- 例外によって`synchronized`の処理から抜けた場合も、ロックは解放される

### synchronizedメソッド

`synchronized`をメソッドに付けることで、メソッド全体を同期処理の対象にできる。

**基本構文**

```java
public synchronized void method() {
    // 処理
}
```

- インスタンスの`synchronized`メソッドでは、`this`のモニターロックを使用する
- 同じインスタンスに対して複数のスレッドがメソッドを呼び出す場合、同じロックを使用する
- 一つのスレッドがロックを保持している間、他のスレッドは同じロックを必要とする処理に入れない
- メソッドの処理が終了すると、モニターロックが自動的に解放される

### 銀行口座の出金例題 - synchronizedメソッド

先ほどの出金処理に`synchronized`を追加する。

**改善したコード例**

```java
public class BankAccount {

    private int balance;

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public synchronized void withdraw(int amount) {

        if (balance >= amount) {

            balance -= amount;

            System.out.println(
                    "出金成功: " + amount
            );

        } else {

            System.out.println(
                    "残高不足: " + amount
            );
        }
    }

    public synchronized int getBalance() {
        return balance;
    }
}
```

**実行例**

```java
BankAccount account = new BankAccount(1000);

Thread thread1 = new Thread(() -> {
    account.withdraw(800);
});

Thread thread2 = new Thread(() -> {
    account.withdraw(800);
});

thread1.start();
thread2.start();

thread1.join();
thread2.join();

System.out.println(account.getBalance());
```

**出力例**

```text
出金成功: 800
残高不足: 800
200
```

※ どちらのスレッドが先に出金するかは保証されない。

**処理の流れ**

1. 2つのスレッドが同じ`BankAccount`インスタンスを共有する
2. `thread1`または`thread2`が先にモニターロックを取得する
3. ロックを取得したスレッドが残高を確認する
4. 出金可能であれば残高を更新する
5. `withdraw()`の終了時にロックを解放する
6. 待機していたスレッドがロックを取得する
7. 更新後の残高を確認する
8. 残高不足であれば出金処理を実行しない

**重要なポイント**

- 残高確認と更新処理をまとめて保護できる
- 共有データの整合性を維持できる
- 2つのスレッドが同じインスタンスを共有しているため、同じモニターロックを使用する
- `synchronized`によって不正な出金を防止できる

### synchronizedコードブロック

`synchronized`は、メソッド全体ではなく、特定のコードブロックに対して使用することもできる。

**基本構文**

```java
synchronized (lock) {
    // 同期が必要な処理
}
```

- `lock`にはモニターロックとして使用するオブジェクトを指定する
- 指定したオブジェクトのモニターロックを取得する
- ロックを取得したスレッドだけがコードブロック内の処理を実行できる
- ブロックから抜けると、モニターロックは自動的に解放される
- メソッド全体ではなく、必要な処理範囲だけを保護できる

**コード例**

```java
public class BankAccount {

    private int balance;

    private final Object lock = new Object();

    public BankAccount(int balance) {
        this.balance = balance;
    }

    public void withdraw(int amount) {

        synchronized (lock) {

            if (balance >= amount) {

                balance -= amount;

                System.out.println("出金成功");

            } else {

                System.out.println("残高不足");
            }
        }
    }

    public int getBalance() {

        synchronized (lock) {
            return balance;
        }
    }
}
```

**処理の流れ**

1. `BankAccount`インスタンスを生成する
2. `lock`オブジェクトが生成される
3. スレッドが`withdraw()`を呼び出す
4. `synchronized (lock)`でモニターロックを取得する
5. 残高確認と更新処理を実行する
6. コードブロックを抜けるとロックを解放する
7. 他のスレッドが同じロックを取得できるようになる

**重要なポイント**

- `lock`はロック対象となるオブジェクト
- `final`によってロック用の参照が別のオブジェクトに変更されないようにしている
- `private`にすることで、外部からロック用オブジェクトを直接扱われることを防げる
- 同じ共有データを保護する処理では、原則として同じロックを使用する必要がある
- ロック対象が異なる場合、同じデータを操作していても適切に排他制御されない

### synchronizedメソッドとコードブロックの違い

| 比較項目 | synchronizedメソッド | synchronizedコードブロック |
| --- | --- | --- |
| 同期範囲 | メソッド全体 | 指定したブロック |
| 宣言方法 | メソッドにキーワードを付ける | `synchronized (lock)` |
| ロック対象 | インスタンスメソッドでは`this` | 指定したオブジェクト |
| 処理範囲の調整 | メソッド単位 | コードブロック単位 |
| 主な特徴 | 記述が簡単 | 同期範囲を細かく制御できる |

**synchronizedメソッド**

```java
public synchronized void increment() {
    count++;
}
```

**synchronizedコードブロック**

```java
public void increment() {

    synchronized (this) {
        count++;
    }
}
```

上記の2つは、インスタンスのモニターロックとして`this`を使用する点で同等。

**注意事項**

- メソッド全体を同期すると、同期が不要な処理までロックの対象になる場合がある
- コードブロックを使用すると、必要な範囲だけを同期できる
- ただし、同期範囲を狭くしすぎると、保護すべき操作が分離されてしまう可能性がある
- 安全性を維持できる範囲で同期処理を設計することが重要

### 共有変数と同期処理

マルチスレッド環境で同期処理が必要かどうかは、変数が複数のスレッド間で共有されるかによって変わる。

Javaの変数には、主に次の種類がある。

1. ローカル変数
2. インスタンスメンバー変数
3. クラス変数（static変数）

### ローカル変数（Local Variable）

- メソッドやコードブロックの内部で宣言される変数
- メソッドの実行ごとに独立したローカル変数が使用される
- 各スレッドは独立したスタック領域を持つ
- スレッドごとに独立して使用するローカル変数は、通常、同期処理を必要としない
- ただし、ローカル変数が共有オブジェクトを参照する場合、そのオブジェクトの状態には注意が必要

**コード例**

```java
public void calculate() {

    int count = 0;

    count++;

    System.out.println(count);
}
```

2つのスレッドが同時に`calculate()`を実行しても、それぞれの呼び出しで独立した`count`が使用される。

**イメージ**

```text
thread-1                 thread-2

calculate()              calculate()
    |                        |
count = 0                count = 0
    |                        |
count++                  count++
    |                        |
count = 1                count = 1
```

**重要なポイント**

- ローカル変数は、各メソッド呼び出しに対して独立している
- 独立したローカル変数だけを操作する場合、通常は`synchronized`が不要
- ただし、ローカル変数に格納された参照が共有オブジェクトを指している場合は、その共有オブジェクトに対する同期が必要になることがある

### インスタンスメンバー変数（Instance Variable）

- クラスの内部で宣言され、`static`が付いていないフィールド
- オブジェクトのインスタンスごとに保持される
- `new`によって別々のインスタンスを生成すると、それぞれ独立したフィールドが存在する
- 複数のスレッドが同じインスタンスを共有する場合、そのインスタンスのフィールドも共有される
- 共有されたインスタンス変数を複数のスレッドが更新する場合、同期処理が必要になることがある

**コード例**

```java
public class Counter {

    private int count = 0;

    public void increment() {
        count++;
    }
}
```

**別々のインスタンスを使用する場合**

```java
Counter counter1 = new Counter();
Counter counter2 = new Counter();

Thread thread1 = new Thread(() -> {
    counter1.increment();
});

Thread thread2 = new Thread(() -> {
    counter2.increment();
});
```

**イメージ**

```text
thread-1                    thread-2
   |                           |
counter1                    counter2
   |                           |
count = 0                   count = 0
   |                           |
count++                     count++
   |                           |
count = 1                   count = 1
```

- `counter1`と`counter2`は異なるインスタンス
- それぞれ独立した`count`を保持している
- この例では、2つのスレッドが同じ`count`を更新するわけではない

**同じインスタンスを共有する場合**

```java
Counter counter = new Counter();

Thread thread1 = new Thread(() -> {
    counter.increment();
});

Thread thread2 = new Thread(() -> {
    counter.increment();
});
```

**イメージ**

```text
thread-1                 thread-2
   |                        |
   +-----------+------------+
               |
             counter
               |
           count = 0
               |
        両方がcount++を実行
               |
         競合する可能性
```

- 2つのスレッドが同じインスタンスを参照している
- `count`は共有されている
- 同期処理がない場合、競合状態が発生する可能性がある

**重要なポイント**

- インスタンス変数はクラス全体で一つではない
- インスタンスごとに存在する
- 同じインスタンスを共有する場合、そのフィールドも共有される
- `static`が付いていなくても、同期処理が必要になる場合がある

### クラス変数（Class Variable）

- `static`で宣言されたフィールド
- 特定のインスタンスではなく、クラスに属する変数
- 同じクラスのインスタンス間で共有される
- 異なるインスタンスを使用するスレッドでも、同じクラス変数にアクセスする場合がある
- 共有されたクラス変数を複数のスレッドが更新する場合、同期処理が必要になることがある

**コード例**

```java
public class Counter {

    private static int count = 0;

    public void increment() {
        count++;
    }
}
```

**異なるインスタンスを使用する場合**

```java
Counter counter1 = new Counter();
Counter counter2 = new Counter();

Thread thread1 = new Thread(() -> {
    counter1.increment();
});

Thread thread2 = new Thread(() -> {
    counter2.increment();
});
```

**イメージ**

```text
thread-1                 thread-2
   |                        |
counter1                 counter2
   |                        |
   +------------+-----------+
                |
         Counterクラス
                |
         static count
                |
         共有された変数
```

**重要なポイント**

- インスタンスが異なっても、`static`フィールドは共有される
- 同じクラス変数を複数のスレッドが更新する場合、競合状態が発生する可能性がある
- インスタンスの`synchronized`メソッドでは、異なるインスタンス間のアクセスをまとめて保護できない

### ローカル変数・インスタンス変数・クラス変数の比較

| 比較項目 | ローカル変数 | インスタンス変数 | クラス変数 |
| --- | --- | --- | --- |
| 宣言場所 | メソッド・ブロック内 | クラス内 | クラス内 |
| static | 使用しない | なし | あり |
| 管理単位 | メソッド呼び出しごと | インスタンスごと | クラスごと |
| スレッド間共有 | 通常は独立 | 同じインスタンスなら共有 | 同じクラスで共有 |
| 同期処理 | 独立していれば不要 | 共有状態の更新時に必要になる場合がある | 共有状態の更新時に必要になる場合がある |

**重要なポイント**

同期処理が必要かどうかは、変数の種類だけで決定するものではない。

- 複数のスレッドから同じデータにアクセスするか
- 共有データが変更されるか
- 複数の操作をまとめて保護する必要があるか
- 他の同期手段で安全性が保証されているか

これらを考慮して判断する必要がある。

### インスタンスのsynchronizedとstatic synchronized

`synchronized`メソッドでは、インスタンスメソッドと静的メソッドでロック対象が異なる。

**インスタンスメソッド**

```java
public synchronized void increment() {
    count++;
}
```

- ロック対象は`this`
- インスタンスごとに異なるモニターロックを使用する
- 異なるインスタンスの`synchronized`メソッドは、互いに排他制御されない

**staticメソッド**

```java
public static synchronized void increment() {
    count++;
}
```

- ロック対象はクラスを表す`Class`オブジェクト
- 同じクラスの静的同期メソッドでは、同じクラスのモニターロックを使用する
- 異なるインスタンスから呼び出しても、同じクラスロックによって排他制御される

**比較表**

| 項目 | synchronized | static synchronized |
| --- | --- | --- |
| メソッドの種類 | インスタンスメソッド | 静的メソッド |
| ロック対象 | `this` | `クラス名.class` |
| 異なるインスタンス間の排他制御 | されない | 同じクラスロックで制御される |
| 主な用途 | インスタンス単位の共有状態 | クラス単位の共有状態 |

**注意事項**

インスタンスの`synchronized`メソッドと`static synchronized`メソッドは、通常、異なるモニターロックを使用する。

そのため、同じクラスに定義されていても、互いに自動的に排他制御されるわけではない。

### synchronizedとThread.State

`synchronized`によるロック待機は、スレッドの状態にも影響する。

**主な状態**

| 状態 | 説明 |
| --- | --- |
| `RUNNABLE` | 実行可能な状態 |
| `BLOCKED` | モニターロックの取得待ち |
| `WAITING` | 時間制限のない待機 |
| `TIMED_WAITING` | 時間制限のある待機 |

**ロック取得待ちのイメージ**

```text
thread-1
RUNNABLE
   |
   | ロック取得
   v
臨界領域を実行
   |
   | ロック解放
   v
RUNNABLE


thread-2
RUNNABLE
   |
   | 同じロックを要求
   v
BLOCKED
   |
   | ロック取得可能
   v
RUNNABLE
   |
   | 臨界領域を実行
```

**重要なポイント**

- `BLOCKED`はモニターロックを取得できずに待機している状態
- `Thread.sleep()`による待機は`TIMED_WAITING`
- `sleep()`を実行しても、保持しているモニターロックは解放されない
- `join()`はスレッドの終了待機であり、モニターロックによる排他制御とは目的が異なる

### synchronizedとvolatileの違い

前の章で学習した`volatile`と`synchronized`は、どちらもマルチスレッド環境で使用する重要な仕組み。

ただし、目的と動作が異なる。

| 比較項目 | volatile | synchronized |
| --- | --- | --- |
| 主な目的 | 可視性・順序性の確保 | 排他制御・可視性の確保 |
| モニターロック | 使用しない | 使用する |
| 排他制御 | できない | できる |
| 複合操作の原子性 | 保証できない | 適切に保護すれば保証できる |
| 他スレッドの変更の可視性 | 保証される | 同じロックによる同期で保証される |
| 主な用途 | 終了フラグ・状態通知 | 共有データの複合的な更新 |

**volatileの例**

```java
private volatile boolean running = true;
```

- 共有変数の変更を他のスレッドから観測できるようにする
- 単純な状態通知などに利用できる
- 排他制御は行わない

**synchronizedの例**

```java
private int count = 0;

public synchronized void increment() {
    count++;
}
```

- 同じモニターロックを使用するスレッド間で排他制御する
- `count++`のような複合操作を保護できる
- ロックの取得・解放によってメモリ可視性も保証される

**重要なポイント**

- `volatile`は排他制御を行わない
- `synchronized`は排他制御とメモリ可視性の両方に関係する
- `volatile`を使用しても、`count++`を安全な複合操作にはできない
- `synchronized`を使用する場合は、適切なロックと同期範囲を設定する必要がある

### synchronizedとHappens-Before

`synchronized`は、Java Memory ModelのHappens-Before関係にも関係する。

- あるスレッドがモニターロックを解放すると、その後に同じモニターロックを取得するスレッドとの間にHappens-Before関係が成立する
- これによって、先行するスレッドのメモリ操作の結果を後続のスレッドが適切に観測できる
- `synchronized`は単なる排他制御だけでなく、共有データの可視性も保証する

**イメージ**

```text
thread-1

ロック取得
    |
共有変数を更新
    |
ロック解放
    |
    | Happens-Before
    v

thread-2

同じロックを取得
    |
更新された共有変数を参照
    |
ロック解放
```

**重要なポイント**

- Happens-Before関係は、単に処理時刻が前後しているという意味ではない
- 同じモニターロックの解放と取得によって、メモリ可視性の保証が得られる
- 共有データのアクセスを適切に同期することが重要

### synchronizedの性能とロック競合

`synchronized`は共有データを安全に操作できる一方で、使用方法によっては処理性能に影響する。

- ロックを取得できないスレッドは待機する必要がある
- 複数のスレッドが同じロックを頻繁に要求する状態をロック競合（Lock Contention）という
- ロック競合が多い場合、スレッドの待機時間が増加する可能性がある
- 同期処理の範囲が広すぎると、並行して実行できる処理が減少する
- 安全性を維持したうえで、同期処理の範囲を適切に設定することが重要

**同期範囲が広い例**

```java
public synchronized void process() {

    // 同期が不要な計算処理
    calculate();

    // 共有データの更新
    count++;

    // 同期が不要な別の処理
    otherProcess();
}
```

この例では、メソッド全体が同期の対象になる。

**同期範囲を限定した例**

```java
public void process() {

    // 同期が不要な計算処理
    calculate();

    synchronized (this) {
        count++;
    }

    // 同期が不要な別の処理
    otherProcess();
}
```

**改善ポイント**

- 共有データの更新処理だけを同期する
- 同期が不要な処理はロックの外側で実行する
- ロックの保持時間を短くできる場合がある
- ただし、複数の操作を一つのまとまりとして保護する必要がある場合は、必要な範囲を分割しない

※ この改善例は、`calculate()`と`otherProcess()`が共有状態を操作せず、同期を必要としないことを前提としている。

### synchronizedを使用する際の注意事項

- `synchronized`は、適切な範囲とロック対象を指定する必要がある
- 同じ共有データを保護する処理では、同じロックを使用することが重要
- インスタンスの`synchronized`メソッドは、`this`のモニターロックを使用する
- 異なるインスタンスは、異なるモニターロックを持つ
- `static synchronized`メソッドは、クラスの`Class`オブジェクトをロック対象とする
- 同期処理を追加しても、異なるロックを使用している場合は競合状態が解決しない可能性がある
- ローカル変数が独立している場合は、通常、同期処理を必要としない
- 共有オブジェクトの状態を変更する場合は、変数の宣言場所だけでなく、実際の共有関係を確認する
- `Thread.sleep()`を使用しても、保持しているモニターロックは自動的に解放されない
- ロックの取得順序は保証されない
- 複数のロックを不適切な順序で取得すると、デッドロックが発生する可能性がある
- `synchronized`は安全性を確保するための仕組みであり、すべての処理を同期すればよいわけではない
- 同期処理の範囲が広すぎる場合は、不要な待機が発生する可能性がある
- 共有データの整合性を維持するために必要な処理を、まとめて保護することが重要

### 同期処理 - synchronizedのまとめ

- 同期処理は、複数のスレッドが共有データを安全に操作するための仕組み
- 複数のスレッドが共有データに同時にアクセスすると、競合状態が発生する可能性がある
- 競合状態（Race Condition）は、スレッドの実行順序によって結果が変化する状態
- 臨界領域（Critical Section）は、複数のスレッドが同時に実行すると問題が発生する可能性がある処理領域
- 排他制御（Mutual Exclusion）によって、同じ臨界領域への同時アクセスを制限できる
- Javaでは`synchronized`を使用して排他制御を実現できる
- `synchronized`は、オブジェクトのモニターロックを利用する
- 同じモニターロックを一度に保持できるスレッドは一つだけ
- モニターロックを取得できないスレッドは`BLOCKED`状態になる
- インスタンスの`synchronized`メソッドでは、`this`のモニターロックを使用する
- `static synchronized`メソッドでは、クラスの`Class`オブジェクトのモニターロックを使用する
- `synchronized`コードブロックでは、ロック対象と同期範囲を明示的に指定できる
- ローカル変数は、通常、メソッドの実行ごとに独立している
- インスタンス変数は、同じインスタンスを共有するスレッド間で共有される
- クラス変数は、同じクラスのインスタンス間で共有される
- `volatile`は可視性と順序性を保証するが、複合操作の排他制御は行わない
- `synchronized`は、適切に保護された処理の原子性とメモリ可視性を確保できる
- 同じモニターロックの解放と取得によってHappens-Before関係が成立する
- ロック競合が発生すると、スレッドの待機時間が増加する可能性がある
- 同期処理は、共有データを安全に保護できる範囲で適切に設計する必要がある
- 銀行口座の出金例題を通して、競合状態の発生原因と`synchronized`による改善方法について学習
- `synchronized`の仕組みを理解することは、今後学習する`Lock`や並行処理におけるスレッドセーフな設計の基礎になる


## 7. 高度な同期処理 - concurrent.Lock

### 高度な同期処理の概要

- Javaでは、`synchronized`以外にもスレッドを制御する仕組みが用意されている
- `java.util.concurrent.locks`パッケージには、より柔軟な同期処理を実現するためのクラスやインターフェースが存在する
- `LockSupport`は、スレッドの待機と再開を制御するためのクラス
- `ReentrantLock`は、共有データへのアクセスを制御するための再入可能な排他ロック
- `LockSupport`による待機制御と、`ReentrantLock`による排他制御は目的が異なる
- 高度な同期処理を理解するためには、スレッドの状態、割り込み、ロックの仕組みを理解する必要がある

### LockSupportの基本概念

`LockSupport`は、スレッドを待機させたり、待機しているスレッドに実行再開の許可を与えたりするためのクラス。

**主なメソッド**

| メソッド | 説明 |
| --- | --- |
| `park()` | 現在のスレッドを待機させる |
| `unpark(Thread)` | 指定したスレッドに許可証を与える |
| `parkNanos(long)` | 指定した時間を上限として待機する |
| `parkUntil(long)` | 指定した期限まで待機する |

**重要なポイント**

- `park()`は現在実行中のスレッドに作用する
- `unpark()`は指定したスレッドに作用する
- `park()`は通常`WAITING`状態になる
- `parkNanos()`による待機は通常`TIMED_WAITING`状態になる
- `LockSupport`はスレッドの待機を制御する仕組みであり、共有データを自動的に保護するわけではない

### LockSupport.park()

`park()`は、現在実行中のスレッドを待機させるためのメソッド。

**コード例**

```java
import java.util.concurrent.locks.LockSupport;

public class LockSupportMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {

            System.out.println("作業開始");

            LockSupport.park();

            System.out.println("作業再開");
        });

        worker.start();

        Thread.sleep(1000);

        LockSupport.unpark(worker);

        worker.join();

        System.out.println("すべての作業終了");
    }
}
```

**出力例**

```text
作業開始
作業再開
すべての作業終了
```

**処理の流れ**

1. `worker`スレッドを生成する
2. `start()`によってスレッドを開始する
3. `worker`が`LockSupport.park()`を呼び出す
4. 許可証がなく、割り込みなども発生していなければ待機する
5. `main`スレッドが`unpark(worker)`を呼び出す
6. `worker`に許可証が与えられる
7. `worker`は待機から復帰して処理を継続する
8. `join()`によってスレッドの終了を待つ

**重要なポイント**

- `park()`の戻り値は`void`
- `park()`は許可証以外の理由でも復帰する可能性がある
- `unpark()`はスレッドの即時実行を保証しない
- 復帰後、実際にCPUで処理を再開するタイミングはスケジューラに依存する
- この例は基本的な動作を確認するためのものであり、実際の待機処理では条件を再確認する必要がある

### LockSupportの許可証（Permit）

`LockSupport`は、スレッドごとに許可証（Permit）を管理する。

許可証は、スレッドが待機せずに処理を進められるかどうかを制御するための仕組み。

**基本的なルール**

- スレッドごとに保持できる許可証は最大1つ
- 許可証がない状態で`park()`を実行すると、通常は待機する
- 許可証がある状態で`park()`を実行すると、許可証を消費して待機せずに復帰する
- `unpark()`を呼び出すと、対象スレッドに許可証を与える
- 許可証がすでにある状態で`unpark()`を呼び出しても、許可証は増えない

**許可証のイメージ**

```text
許可証なし（0）
      |
      | unpark(thread)
      v
許可証あり（1）
      |
      | park()
      v
許可証なし（0）
```

**重要なポイント**

`unpark()`を2回実行しても、許可証が2つ蓄積されるわけではない。

許可証の状態は基本的に`0`または`1`となる。

### unpark()を先に実行した場合

`LockSupport`では、`unpark()`を`park()`より先に呼び出すことができる。

**コード例**

```java
import java.util.concurrent.locks.LockSupport;

public class PermitMain {

    public static void main(String[] args) {

        Thread thread = Thread.currentThread();

        LockSupport.unpark(thread);

        System.out.println("park開始");

        LockSupport.park();

        System.out.println("park終了");
    }
}
```

**出力**

```text
park開始
park終了
```

**処理の流れ**

1. `main`スレッドを取得する
2. `unpark()`によって許可証を与える
3. `park()`を呼び出す
4. すでに許可証が存在するため、待機せずに復帰する
5. 許可証は消費される

**重要なポイント**

- `unpark()`を先に実行しても問題ない
- 許可証が残っていれば、次の`park()`で使用される
- 許可証はスレッドごとに管理される
- `unpark()`はスレッドを強制的に実行させるメソッドではない

### park()による待機とスレッド状態

`LockSupport.park()`によって待機したスレッドは、通常`WAITING`状態になる。

```text
RUNNABLE
    |
    | LockSupport.park()
    v
WAITING
    |
    | unpark()などで復帰
    v
RUNNABLE
```

**synchronizedとの違い**

- `synchronized`のモニターロック取得待ちでは`BLOCKED`状態になる
- `LockSupport.park()`では通常`WAITING`状態になる
- どちらも待機する場合があるが、待機する理由と仕組みが異なる

### LockSupportと割り込み

`LockSupport.park()`による待機中に割り込みが発生すると、待機から復帰することがある。

**コード例**

```java
import java.util.concurrent.locks.LockSupport;

public class ParkInterruptMain {

    public static void main(String[] args)
            throws InterruptedException {

        Thread worker = new Thread(() -> {

            System.out.println("待機開始");

            LockSupport.park();

            System.out.println("待機終了");

            System.out.println(
                    "割り込み状態: "
                    + Thread.currentThread().isInterrupted()
            );
        });

        worker.start();

        Thread.sleep(1000);

        worker.interrupt();

        worker.join();
    }
}
```

**出力例**

```text
待機開始
待機終了
割り込み状態: true
```

**重要なポイント**

- `park()`による待機は割り込みによって解除される場合がある
- `park()`自体は`InterruptedException`をスローしない
- 割り込みによって`park()`から復帰しても、割り込みフラグはクリアされない
- 割り込み状態が`true`のまま再度`park()`を呼び出すと、すぐに復帰する場合がある
- 割り込みをどう扱うかは、呼び出し側で適切に判断する必要がある

### sleep()とpark()の違い

| 比較項目 | `Thread.sleep()` | `LockSupport.park()` |
| --- | --- | --- |
| 主な目的 | 指定時間の待機 | 許可証などに基づく待機 |
| 時間指定 | 基本的に必要 | 不要 |
| スレッド状態 | `TIMED_WAITING` | 通常`WAITING` |
| 待機解除 | 時間経過・割り込みなど | 許可証・割り込み・その他の理由 |
| 割り込み例外 | `InterruptedException` | スローしない |
| 割り込みフラグ | 例外発生時にクリア | 割り込みによる復帰ではクリアしない |

**重要なポイント**

- `sleep()`は時間を基準とした待機
- `park()`は許可証を利用した待機
- `park()`は予期せず復帰する可能性があるため、待機条件をループで確認する設計が重要
- どちらも、それ自体では共有データへの排他制御を実現しない

### ReentrantLockの基本概念

`ReentrantLock`は、明示的にロックの取得と解放を制御するためのクラス。

`java.util.concurrent.locks`パッケージに含まれている。

**基本概念**

- `synchronized`と同様に排他制御を実現できる
- `lock()`によってロックを取得する
- `unlock()`によってロックを解放する
- ロックを取得しているスレッドだけが、同じロックで保護された処理を実行できる
- ロックを取得できないスレッドは待機する
- 再入可能なロックであり、同じスレッドが同じロックを繰り返し取得できる
- ロック取得の試行、時間制限、割り込み可能な待機などを利用できる

**主なメソッド**

| メソッド | 説明 |
| --- | --- |
| `lock()` | ロックを取得するまで待機する |
| `unlock()` | ロックを解放する |
| `tryLock()` | ロックの即時取得を試みる |
| `tryLock(time, unit)` | 指定時間内でロック取得を試みる |
| `lockInterruptibly()` | 割り込み可能な状態でロック取得を待つ |
| `isLocked()` | ロックが取得されているか確認する |
| `isHeldByCurrentThread()` | 現在のスレッドがロックを保持しているか確認する |

### ReentrantLockの基本的な使用方法

**コード例**

```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantLockMain {

    private static final ReentrantLock lock =
            new ReentrantLock();

    private static int count = 0;

    public static void increment() {

        lock.lock();

        try {
            count++;
        } finally {
            lock.unlock();
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        Thread thread1 = new Thread(
                ReentrantLockMain::increment
        );

        Thread thread2 = new Thread(
                ReentrantLockMain::increment
        );

        thread1.start();
        thread2.start();

        thread1.join();
        thread2.join();

        System.out.println("count = " + count);
    }
}
```

**出力**

```text
count = 2
```

**処理の流れ**

1. `ReentrantLock`インスタンスを生成する
2. 2つのスレッドが同じロックを共有する
3. 一方のスレッドが`lock()`でロックを取得する
4. ロックを取得したスレッドが`count++`を実行する
5. `finally`でロックを解放する
6. 他のスレッドがロックを取得する
7. 同じ処理を実行する
8. すべての処理が終了すると`count`は`2`になる

**重要なポイント**

- 2つのスレッドが同じ`ReentrantLock`インスタンスを使用している
- `count++`全体を排他制御できる
- ロックを取得した場合は、必ず解放する必要がある
- `synchronized`とは異なり、ロックを明示的に解放する

### try-finallyによるロック解放

`ReentrantLock`を使用する場合は、`try-finally`によるロック解放が重要。

**推奨される基本構文**

```java
lock.lock();

try {
    // ロックで保護する処理
} finally {
    lock.unlock();
}
```

**理由**

- `try`内の処理で例外が発生しても`finally`が実行される
- ロックを解放し忘れる問題を防止できる
- ロックが解放されないまま残ると、他のスレッドが待機し続ける可能性がある

**注意事項**

- `unlock()`はロックを取得したスレッドが実行する必要がある
- ロックを保持していないスレッドが`unlock()`すると`IllegalMonitorStateException`が発生する
- ロック取得に成功したことを確認してから`unlock()`する必要がある

### 再入可能ロック（Reentrant Lock）

`ReentrantLock`は、同じスレッドが同じロックを複数回取得できる。

これを再入可能（Reentrant）という。

**コード例**

```java
import java.util.concurrent.locks.ReentrantLock;

public class ReentrantMain {

    private static final ReentrantLock lock =
            new ReentrantLock();

    public static void main(String[] args) {

        lock.lock();

        try {
            System.out.println("1回目のロック取得");

            lock.lock();

            try {
                System.out.println("2回目のロック取得");
            } finally {
                lock.unlock();
            }

        } finally {
            lock.unlock();
        }
    }
}
```

**出力**

```text
1回目のロック取得
2回目のロック取得
```

**重要なポイント**

- 同じスレッドであれば、同じロックを複数回取得できる
- ロック内部で再び同じロックを取得しても、それだけではデッドロックにならない
- 取得した回数だけ`unlock()`する必要がある
- ロック取得回数が0になると、他のスレッドがロックを取得できるようになる
- `synchronized`のモニターロックも再入可能である

### ReentrantLockとスレッド状態

`ReentrantLock`のロックを取得できない場合、スレッドは内部の待機機構によって待機する。

**基本的な流れ**

```text
thread-1
    |
    | lock()
    v
ロック取得
    |
    | 臨界領域を実行
    v
unlock()
    |
    v
ロック解放


thread-2
    |
    | lock()
    v
ロック取得不可
    |
    v
WAITINGなど
    |
    | ロック取得可能
    v
RUNNABLE
    |
    v
臨界領域を実行
```

**重要なポイント**

- `synchronized`のモニターロック取得待ちは`BLOCKED`
- `ReentrantLock`の待機は内部で`LockSupport`などを利用する
- `ReentrantLock.lock()`の取得待ちは通常`WAITING`状態になる
- 時間制限付きの取得待ちは`TIMED_WAITING`状態になる場合がある
- 実際のスレッド状態は、内部の処理や取得タイミングによって変化する

### tryLock()によるロック取得

`tryLock()`は、ロックの取得を試み、取得できたかどうかを`boolean`で返すメソッド。

**基本構文**

```java
if (lock.tryLock()) {

    try {
        System.out.println("ロック取得成功");
    } finally {
        lock.unlock();
    }

} else {
    System.out.println("ロック取得失敗");
}
```

**重要なポイント**

- ロックを取得できた場合は`true`
- ロックを取得できなかった場合は`false`
- 引数なしの`tryLock()`は、取得できなくてもロック解放を待ち続けない
- ロック取得に成功した場合だけ`unlock()`する
- 他の処理へ進みたい場合や、ロック取得に失敗した場合の処理を分けたいときに利用できる

### 時間制限付きtryLock()

`tryLock(long time, TimeUnit unit)`を使用すると、ロックの取得待ちに時間制限を設定できる。

**コード例**

```java
import java.util.concurrent.TimeUnit;

boolean acquired =
        lock.tryLock(1, TimeUnit.SECONDS);

if (acquired) {

    try {
        System.out.println("ロック取得成功");
    } finally {
        lock.unlock();
    }

} else {
    System.out.println("タイムアウト");
}
```

※ このコードは`InterruptedException`を処理または宣言する必要がある。

**重要なポイント**

- 指定時間内にロックを取得できれば`true`
- 指定時間内に取得できなければ`false`
- 待機中に割り込みを受けた場合、`InterruptedException`が発生する
- ロックを取得できなかった場合は`unlock()`しない

### 公平ロック（Fair Lock）

`ReentrantLock`では、公平性を考慮したロックを生成できる。

**非公平ロック**

```java
ReentrantLock lock = new ReentrantLock();
```

**公平ロック**

```java
ReentrantLock lock = new ReentrantLock(true);
```

**公平ロックと非公平ロックの比較**

| 比較項目 | 非公平ロック | 公平ロック |
| --- | --- | --- |
| 生成方法 | `new ReentrantLock()` | `new ReentrantLock(true)` |
| 基本方針 | 待機順序を優先しない | 長く待機しているスレッドを優先する |
| スループット | 比較的高くなりやすい | 低下する場合がある |
| 待機順序 | 保証されない | 公平性を考慮する |

**重要なポイント**

- 公平ロックは、長く待っているスレッドを優先するように設計されている
- すべてのスレッドの実行順序が完全に保証されるわけではない
- 公平性の管理によって処理性能が低下する場合がある
- 引数なしの`tryLock()`は、公平ロックでも公平性を考慮せずに取得できる場合がある
- 公平性と処理性能のバランスを考えて選択する

### ReentrantLockの待機中断

`ReentrantLock`では、ロック取得待ちの間に割り込みを処理する方法を選択できる。

**lock()**

- ロックを取得できるまで待機する
- 待機中に割り込みが発生しても、割り込みだけを理由にロック取得待ちを終了しない
- ロック取得待ちで`InterruptedException`をスローしない
- 割り込み状態は保持される

**lockInterruptibly()**

- ロックを取得できるまで待機する
- 割り込みを受けた場合、ロック取得待ちを中断できる
- 割り込みによって`InterruptedException`が発生する
- `InterruptedException`が発生すると、割り込みフラグは通常クリアされる

### lockInterruptibly()の使用方法

**コード例**

```java
import java.util.concurrent.locks.ReentrantLock;

public class LockInterruptMain {

    public static void main(String[] args)
            throws InterruptedException {

        ReentrantLock lock = new ReentrantLock();

        lock.lock();

        Thread worker = new Thread(() -> {

            try {
                System.out.println("ロック取得待機");

                lock.lockInterruptibly();

                try {
                    System.out.println("ロック取得成功");
                } finally {
                    lock.unlock();
                }

            } catch (InterruptedException e) {

                System.out.println("割り込み発生");

                Thread.currentThread().interrupt();
            }
        });

        try {
            worker.start();

            Thread.sleep(1000);

            worker.interrupt();
        } finally {
            lock.unlock();
        }

        worker.join();
    }
}
```

**出力例**

```text
ロック取得待機
割り込み発生
```

**処理の流れ**

1. `main`スレッドがロックを取得する
2. `worker`スレッドを開始する
3. `worker`が`lockInterruptibly()`を呼び出す
4. `main`がロックを保持しているため、`worker`は待機する
5. `main`が`worker.interrupt()`を呼び出す
6. `worker`に`InterruptedException`が発生する
7. `worker`はロックを取得せずに待機を終了する
8. `catch`ブロックで割り込みを処理する
9. `main`が保持していたロックを解放する

**重要なポイント**

- `lockInterruptibly()`は、割り込み可能なロック取得方法
- 割り込みによって待機を中断できる
- ロック取得に失敗した場合は`unlock()`してはいけない
- ロック取得後に例外が発生する場合に備え、`try-finally`で解放する
- `InterruptedException`の処理方針に応じて、必要なら割り込み状態を復元する

### lock()とlockInterruptibly()の違い

| 比較項目 | `lock()` | `lockInterruptibly()` |
| --- | --- | --- |
| 主な目的 | ロック取得 | 割り込み可能なロック取得 |
| ロック取得待機 | する | する |
| 割り込みによる待機中断 | しない | できる |
| `InterruptedException` | スローしない | スローする |
| 割り込みフラグ | 割り込み状態を保持 | 例外発生時にクリア |
| 使用場面 | 通常の排他制御 | キャンセル可能な待機処理 |

**重要なポイント**

`lock()`に対して割り込みを送信しても、ロック取得待ちを中断することはできない。

一方、`lockInterruptibly()`を使用すると、ロックの取得待ちを中断できる。

この違いは、スレッドの終了要求に応答できる設計を考える際に重要となる。

### synchronizedとReentrantLockの違い

| 比較項目 | `synchronized` | `ReentrantLock` |
| --- | --- | --- |
| ロックの種類 | モニターロック | 明示的なLockオブジェクト |
| ロック取得 | 自動 | `lock()`など |
| ロック解放 | 自動 | `unlock()` |
| 再入可能 | 可能 | 可能 |
| ロック取得待ち | `BLOCKED` | 通常`WAITING`など |
| 待機中の割り込み | モニター取得待ちは中断できない | `lockInterruptibly()`で中断可能 |
| ロック取得の試行 | 直接的な仕組みはない | `tryLock()` |
| 取得タイムアウト | 直接指定できない | 時間指定`tryLock()` |
| 公平性の設定 | できない | 可能 |
| 基本的な特徴 | 記述が簡潔 | 柔軟な制御が可能 |

**synchronizedの例**

```java
public synchronized void increment() {
    count++;
}
```

**ReentrantLockの例**

```java
lock.lock();

try {
    count++;
} finally {
    lock.unlock();
}
```

**重要なポイント**

- どちらも共有データへの排他制御に使用できる
- 適切なロックの取得と解放によってメモリ可視性も保証される
- `synchronized`はロックの解放が自動的に行われる
- `ReentrantLock`ではロックを明示的に管理する
- `ReentrantLock`は、割り込みやタイムアウトなどの柔軟な制御が必要な場合に利用できる
- 共有データを保護するためには、複数のスレッドが同じロックを使用することが重要

### LockSupportとReentrantLockの違い

`LockSupport`と`ReentrantLock`は、名前が似ていても役割が異なる。

| 比較項目 | LockSupport | ReentrantLock |
| --- | --- | --- |
| 主な目的 | スレッドの待機と再開 | 排他制御 |
| 主なメソッド | `park()`、`unpark()` | `lock()`、`unlock()` |
| 共有データの保護 | 自動的には行わない | 適切に使用すれば可能 |
| 待機制御 | 直接制御する | ロック取得に応じて制御する |
| 主な用途 | 同期機構の構築など | 臨界領域の保護 |

**重要なポイント**

- `LockSupport`はスレッドを待機させるための低レベルな仕組み
- `ReentrantLock`は共有データを安全に操作するためのロック
- `ReentrantLock`の内部では、`LockSupport`などを利用した待機処理が使用される
- `LockSupport`を使用しただけでは、`count++`などの競合状態は防止できない

### 高度な同期処理を使用する際の注意事項

- 同期処理が必要かどうかは、共有データへのアクセス状況を確認して判断する
- `LockSupport`は排他制御そのものを提供するわけではない
- `park()`から復帰しても、待機条件が成立したとは限らない
- 許可証はスレッドごとに最大1つしか保持されない
- `ReentrantLock`ではロックの解放を忘れないようにする
- `unlock()`はロックを保持しているスレッドが実行する
- ロック取得に成功した場合だけ`unlock()`を実行する
- `try-finally`を使用して、例外発生時のロック解放を保証する
- `lock()`では待機中の割り込みによってロック取得を中断できない
- `lockInterruptibly()`では待機中の割り込みに対応できる
- 複数のロックを異なる順序で取得すると、デッドロックが発生する可能性がある
- 必要以上にロックを保持すると、スレッドの待機時間が増加する可能性がある

### 高度な同期処理 - concurrent.Lockのまとめ

- Javaには`synchronized`以外にも高度な同期処理を実現する仕組みがある
- `LockSupport`はスレッドの待機と再開を制御するクラス
- `park()`は現在のスレッドを待機させる
- `unpark()`は指定したスレッドに許可証を与える
- 許可証はスレッドごとに最大1つ保持される
- `unpark()`を`park()`より先に実行することもできる
- `park()`は割り込みなどでも復帰する可能性がある
- `park()`による割り込みからの復帰では、割り込みフラグはクリアされない
- `ReentrantLock`は明示的なロック取得・解放によって排他制御を実現する
- `ReentrantLock`は再入可能なロックである
- `lock()`でロックを取得し、`unlock()`で解放する
- `try-finally`を使用して、確実にロックを解放することが重要
- `tryLock()`を使用すると、ロック取得に失敗した場合の処理を分けられる
- 時間指定の`tryLock()`によって、取得待ちにタイムアウトを設定できる
- 公平ロックでは、長く待機しているスレッドの取得を優先する
- `lock()`では割り込みによってロック取得待ちを中断しない
- `lockInterruptibly()`では、割り込みによってロック取得待ちを中断できる
- `synchronized`と`ReentrantLock`は、どちらも排他制御とメモリ可視性を確保できる
- `LockSupport`と`ReentrantLock`は、待機制御と排他制御という異なる役割を持つ
- 高度な同期処理を理解することは、生産者・消費者問題や並行処理コレクションの学習につながる


## 8. 生産者・消費者問題1

### 生産者・消費者問題（Producer-Consumer Problem）

生産者・消費者問題は、複数のスレッドが共有するキューを利用して、データの生成と消費を行う際に発生する同期処理の問題。

- 生産者（Producer）はデータを生成してキューに追加する
- 消費者（Consumer）はキューからデータを取り出して処理する
- 生産者と消費者は同じキューを共有する
- 複数のスレッドが同じデータを操作するため、適切な同期処理が必要になる
- キューの状態によって、生産者または消費者を待機させる必要がある
- 単なる排他制御だけでなく、スレッド間の待機と通知が重要になる

**基本的なイメージ**

```text
生産者（Producer）
        |
        | データを生成
        v
+-------------------+
|    共有キュー      |
| [A] [B] [C]       |
+-------------------+
        |
        | データを取得
        v
消費者（Consumer）
```

**重要なポイント**

生産者と消費者は、それぞれ独立したスレッドとして動作する。

そのため、どちらが先に実行されるかは保証されない。

キューの状態に応じて適切に待機・再開できる仕組みが必要になる。

### 有界キュー（Bounded Queue）

有界キューは、格納できるデータ数に上限が設定されたキュー。

- キューには最大容量が存在する
- 生産者はキューにデータを追加する
- 消費者はキューからデータを取り出す
- キューが満杯の場合、新しいデータを追加できない
- キューが空の場合、データを取り出せない
- キューの容量を超えないように制御する必要がある

**容量が3の場合**

```text
空の状態

[   ][   ][   ]

データを2つ追加

[ A ][ B ][   ]

満杯の状態

[ A ][ B ][ C ]

これ以上追加できない
```

**キューの状態と必要な処理**

| キューの状態 | 生産者 | 消費者 |
| --- | --- | --- |
| 空 | 追加可能 | 待機が必要 |
| データあり | 容量に余裕があれば追加可能 | 取得可能 |
| 満杯 | 待機が必要 | 取得可能 |

**重要なポイント**

- 生産者はキューが満杯の場合に待機する
- 消費者はキューが空の場合に待機する
- データが追加または削除された場合、待機中のスレッドが処理を進められる可能性がある

### 生産者・消費者問題 - 例題1

生産者と消費者が共有キューを操作する場合の基本的な問題について考える。

**基本的な処理**

```text
生産者

1. キューの状態を確認
2. 空きがあればデータを追加
3. 満杯なら待機


消費者

1. キューの状態を確認
2. データがあれば取得
3. 空なら待機
```

共有キューを複数のスレッドから操作する場合、競合状態を防ぐために`synchronized`による排他制御が必要になる。

ただし、ロックを保持したまま待機すると問題が発生する。

### 例題1 - 生産者優先

生産者が先に実行され、キューが満杯になった場合を考える。

**処理の流れ**

1. 生産者がモニターロックを取得する
2. 生産者がキューにデータを追加する
3. キューが満杯になる
4. 生産者が追加処理のために再びロックを取得する
5. キューが満杯なので、ロックを保持したまま待機する
6. 消費者がデータを取得しようとする
7. 消費者は同じモニターロックを取得できない
8. 消費者がデータを取り出せないため、キューに空きができない

**問題のイメージ**

```text
共有キュー

[A][B][C] ← 満杯

生産者
  |
  | ロック取得
  | キューが満杯
  | ロックを保持したまま待機
  v
 待機中

消費者
  |
  | ロック取得を試みる
  v
 BLOCKED
```

**問題点**

- 生産者がモニターロックを保持したまま待機している
- 消費者が同じロックを取得できない
- 消費者がキューからデータを取り出せない
- キューに空きができないため、生産者も処理を進められない

**重要なポイント**

`synchronized`で排他制御していても、ロックを保持したまま待機すると処理が進まなくなる場合がある。

### 例題1 - 消費者優先

消費者が先に実行され、キューが空の状態でデータを取得しようとした場合を考える。

**処理の流れ**

1. 消費者がモニターロックを取得する
2. キューが空であることを確認する
3. 消費者がロックを保持したまま待機する
4. 生産者がデータを追加しようとする
5. 生産者は同じモニターロックを取得できない
6. 生産者がデータを追加できない
7. キューが空のままなので、消費者も処理を進められない

**問題のイメージ**

```text
共有キュー

[ ][ ][ ] ← 空

消費者
  |
  | ロック取得
  | キューが空
  | ロックを保持したまま待機
  v
 待機中

生産者
  |
  | ロック取得を試みる
  v
 BLOCKED
```

**重要なポイント**

生産者が先に実行された場合でも、消費者が先に実行された場合でも、ロックを保持したまま待機すると問題が発生する可能性がある。

※ ここでの「生産者優先」「消費者優先」は、実行順序を想定した例であり、`Thread.setPriority()`による優先度設定とは異なる。

### Thread.sleep()とモニターロック

`Thread.sleep()`は、現在実行中のスレッドを指定時間だけ待機させるメソッド。

ただし、`sleep()`を呼び出しても、保持しているモニターロックは解放されない。

**問題のあるコード例**

```java
public synchronized void put(String data) {

    while (queue.size() == maxSize) {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return;
        }
    }

    queue.add(data);
}
```

**問題点**

- `put()`は`synchronized`メソッドである
- メソッドに入るとモニターロックを取得する
- キューが満杯の場合、`sleep()`で待機する
- `sleep()`中もモニターロックを保持する
- 同じロックを使用する消費者がキューにアクセスできない

**重要なポイント**

`Thread.sleep()`は、時間を指定してスレッドを待機させるためのメソッドであり、ロックを解放して他のスレッドに処理を任せるためのメソッドではない。

### 生産者・消費者問題 - 例題2

例題2では、キューの状態を繰り返し確認しながら待機する方法について考える。

**ビジーウェイト（Busy Waiting）**

ビジーウェイトは、条件が成立するまで繰り返し状態を確認する待機方法。

**概念的なコード例**

```java
while (queue.size() == maxSize) {
    // キューに空きができるまで確認
}
```

**問題点**

- 条件を繰り返し確認するため、CPUを消費する
- `synchronized`の内部で実行すると、ロックを保持し続ける
- 他のスレッドがキューの状態を変更できなくなる場合がある
- 結果として、待機条件がいつまでも変化しない可能性がある

**重要なポイント**

単純な繰り返し処理や`sleep()`では、生産者と消費者の適切な協調動作を実現できない場合がある。

待機中にモニターロックを解放する仕組みが必要になる。

### Object.wait()の基本概念

`Object.wait()`は、現在のスレッドを待機させるためのメソッド。

`wait()`を呼び出すと、対象オブジェクトのモニターロックを解放して待機する。

**主な特徴**

- `Object`クラスに定義されているメソッド
- 対象オブジェクトのモニターロックを保持している必要がある
- `wait()`を呼び出すと、そのモニターロックを解放する
- 通常、スレッドは`WAITING`状態になる
- 他のスレッドが同じモニターを利用できるようになる
- 通知などで待機から復帰する場合、モニターロックを再取得する必要がある

**基本構文**

```java
synchronized (lock) {
    lock.wait();
}
```

**処理の流れ**

```text
RUNNABLE
   |
   | モニターロック取得
   v
synchronized実行
   |
   | wait()
   v
モニターロック解放
   |
   v
WAITING
   |
   | notify()などで通知
   v
ロック再取得待ち
   |
   | ロック取得
   v
RUNNABLE
```

**重要なポイント**

`wait()`は、単にスレッドを待機させるだけではない。

待機する際に、対象オブジェクトのモニターロックを解放することが重要。

これによって、他のスレッドが共有データを操作できるようになる。

### Object.notify()の基本概念

`Object.notify()`は、同じオブジェクトのモニターで待機しているスレッドの一つに通知するメソッド。

**主な特徴**

- `Object`クラスに定義されている
- 同じモニターで`wait()`しているスレッドが通知対象になる
- 待機中のスレッドのうち、一つが通知される
- どのスレッドが選ばれるかは保証されない
- 通知されたスレッドがすぐに処理を実行するとは限らない
- 通知されたスレッドは、モニターロックを再取得してから処理を再開する

**基本構文**

```java
synchronized (lock) {
    lock.notify();
}
```

**重要なポイント**

`notify()`は、待機しているスレッドに通知するメソッドであり、モニターロックを即座に解放するメソッドではない。

### wait()とnotify()の処理の流れ

2つのスレッドが同じモニターロックを使用する場合を考える。

**基本的な流れ**

```text
thread-1
   |
   | ロック取得
   | wait()
   v
ロック解放
   |
   v
WAITING


thread-2
   |
   | ロック取得
   | 共有データを変更
   | notify()
   |
   | ロックを保持したまま処理継続
   |
   | ロック解放
   v


thread-1
   |
   | ロック再取得
   v
RUNNABLE
   |
   | 待機条件を再確認
   v
処理再開
```

**重要なポイント**

- `wait()`はモニターロックを解放して待機する
- `notify()`は待機中のスレッドの一つに通知する
- `notify()`を呼び出しても、通知した側のスレッドはすぐにロックを解放しない
- 通知されたスレッドは、ロックを再取得した後に処理を再開する

### Thread.sleep()とObject.wait()の違い

| 比較項目 | `Thread.sleep()` | `Object.wait()` |
| --- | --- | --- |
| 所属クラス | Thread | Object |
| 主な目的 | 時間による待機 | スレッド間の協調待機 |
| モニターロック解放 | しない | 対象オブジェクトのロックを解放 |
| 待機状態 | `TIMED_WAITING` | 通常`WAITING` |
| synchronized内での実行 | 必須ではない | 対象モニターの保持が必要 |
| 待機解除 | 時間経過・割り込み | 通知・割り込み・その他の理由 |
| InterruptedException | 発生する | 発生する |

**重要なポイント**

`sleep()`と`wait()`は、どちらもスレッドを待機させることができる。

しかし、モニターロックを解放するかどうかが大きな違いとなる。

### wait()とnotify()を使用する条件

`wait()`と`notify()`を使用する場合、対象オブジェクトのモニターロックを保持する必要がある。

**正しい使用例**

```java
private final Object lock = new Object();

public void method() throws InterruptedException {

    synchronized (lock) {
        lock.wait();
    }
}
```

**問題のある使用例**

```java
private final Object lock = new Object();

public void method() throws InterruptedException {

    lock.wait();
}
```

**問題点**

- 対象オブジェクトのモニターロックを保持していない
- `wait()`を呼び出す条件を満たしていない
- `IllegalMonitorStateException`が発生する

**重要なポイント**

- `wait()`と`notify()`は、対象オブジェクトのモニターロックを保持した状態で呼び出す
- 呼び出すオブジェクトとロック対象を一致させる必要がある
- `wait()`は`InterruptedException`に対応する必要がある
- `wait()`は予期せず復帰する場合もあるため、待機条件を再確認することが重要

### 生産者・消費者問題 - 例題3

例題3では、`Object.wait()`と`Object.notify()`を使用して、生産者と消費者の待機・通知を制御する。

**学習用の実装例**

```java
import java.util.ArrayDeque;
import java.util.Queue;

public class BoundedQueueExample {

    private final Queue<String> queue = new ArrayDeque<>();

    private final int maxSize;

    public BoundedQueueExample(int maxSize) {
        if (maxSize <= 0) {
            throw new IllegalArgumentException();
        }
        this.maxSize = maxSize;
    }

    public synchronized void put(String data)
            throws InterruptedException {

        while (queue.size() == maxSize) {
            wait();
        }

        queue.add(data);

        notify();
    }

    public synchronized String take()
            throws InterruptedException {

        while (queue.isEmpty()) {
            wait();
        }

        String data = queue.remove();

        notify();

        return data;
    }
}
```

※ このコードは`wait()`と`notify()`の基本動作を確認するための独立した学習例であり、講義の実際のソースコードそのものではない。

※ 複数の生産者・消費者が存在する場合、この実装には後述する`notify()`の問題が残る。

**put()の処理**

1. 生産者がモニターロックを取得する
2. キューが満杯か確認する
3. 満杯の場合は`wait()`でロックを解放して待機する
4. 空きができた場合はロックを再取得する
5. キューの状態を再確認する
6. データを追加する
7. `notify()`で待機中のスレッドに通知する
8. メソッドを終了してロックを解放する

**take()の処理**

1. 消費者がモニターロックを取得する
2. キューが空か確認する
3. 空の場合は`wait()`でロックを解放して待機する
4. データが追加された場合はロックを再取得する
5. キューの状態を再確認する
6. データを取り出す
7. `notify()`で待機中のスレッドに通知する
8. メソッドを終了してロックを解放する

### 例題3 - 生産者優先

生産者が先に実行され、キューが満杯になる場合を考える。

**処理の流れ**

1. 生産者がキューにデータを追加する
2. キューが満杯になる
3. 生産者が新しいデータを追加しようとする
4. キューが満杯なので`wait()`を呼び出す
5. 生産者がモニターロックを解放して待機する
6. 消費者がモニターロックを取得する
7. 消費者がキューからデータを取り出す
8. 消費者が`notify()`を実行する
9. 消費者がモニターロックを解放する
10. 通知された生産者がロックを再取得する
11. 生産者が空きを確認してデータを追加する

**重要なポイント**

生産者が待機する際にロックを解放するため、消費者がキューからデータを取り出せる。

これによって、ロックを保持したまま待機していた問題を改善できる。

### 例題3 - 消費者優先

消費者が先に実行され、キューが空の場合を考える。

**処理の流れ**

1. 消費者がモニターロックを取得する
2. キューが空であることを確認する
3. 消費者が`wait()`を呼び出す
4. 消費者がモニターロックを解放して待機する
5. 生産者がモニターロックを取得する
6. 生産者がキューにデータを追加する
7. 生産者が`notify()`を実行する
8. 生産者がモニターロックを解放する
9. 通知された消費者がロックを再取得する
10. 消費者がデータの存在を確認する
11. 消費者がデータを取り出す

**重要なポイント**

消費者が先に実行されても、`wait()`によってロックを解放できる。

そのため、生産者が新しいデータを追加できるようになる。

### wait()でwhileを使用する理由

`wait()`から復帰したからといって、必ず待機条件が成立しているわけではない。

**推奨される書き方**

```java
while (queue.isEmpty()) {
    wait();
}
```

**理由**

- `notify()`で通知されても、すぐにロックを取得できるとは限らない
- ロック取得前に、別のスレッドがキューの状態を変更する可能性がある
- `wait()`は通知以外の理由で復帰する場合もある
- 復帰後に条件を再確認する必要がある

**重要なポイント**

待機条件は`if`ではなく`while`で繰り返し確認することが基本となる。

### Object.notify()の限界

`notify()`は、同じモニターで待機しているスレッドのうち、一つを通知する。

しかし、生産者と消費者が複数存在する場合、通知したい種類のスレッドが選ばれるとは限らない。

**問題が発生する可能性がある例**

```text
生産者A → WAITING
生産者B → WAITING
消費者A → WAITING
消費者B → 実行中
```

消費者Bがキューからデータを取り出し、`notify()`を呼び出した場合を考える。

本来は、キューに空きができたため、生産者を起こしたい。

しかし、`notify()`は待機しているスレッドの種類を指定できない。

そのため、消費者Aが通知される可能性もある。

**発生する問題**

- 処理を進められないスレッドが通知される場合がある
- 通知されたスレッドが条件を確認し、再び待機する場合がある
- 実行可能な生産者が待機し続ける可能性がある
- 状況によっては、すべてのスレッドが待機して処理が進まなくなる

**重要なポイント**

`notify()`で通知されたスレッドが、必ず必要な処理を実行できるとは限らない。

そのため、`wait()`と`notify()`を使用するだけでは、複数の生産者・消費者が存在する場合の問題を完全には解決できない。

### synchronizedとwait()・notify()の役割

| 仕組み | 主な役割 |
| --- | --- |
| `synchronized` | 共有データへの排他制御 |
| `wait()` | モニターロックを解放して待機 |
| `notify()` | 待機中のスレッドの一つに通知 |
| `Thread.sleep()` | 指定時間だけ待機 |
| `LockSupport.park()` | 許可証などに基づいて待機 |

**重要なポイント**

`synchronized`は共有データを安全に操作するための仕組み。

`wait()`と`notify()`は、共有データの状態に応じてスレッドを待機・再開させるための仕組み。

排他制御とスレッド間の協調動作は、異なる役割を持つ。

### 生産者・消費者問題1のまとめ

- 生産者はデータを生成して共有キューに追加する
- 消費者は共有キューからデータを取得する
- 有界キューには格納可能なデータ数の上限がある
- キューが満杯の場合、生産者は待機する必要がある
- キューが空の場合、消費者は待機する必要がある
- 複数のスレッドがキューを共有する場合、排他制御が必要になる
- `synchronized`によって共有キューへのアクセスを保護できる
- ロックを保持したまま待機すると、他のスレッドが処理を進められなくなる場合がある
- `Thread.sleep()`は保持しているモニターロックを解放しない
- ビジーウェイトはCPUを消費し、ロックの保持方法によっては処理が進まなくなる
- `Object.wait()`は対象オブジェクトのモニターロックを解放して待機する
- `wait()`による時間制限のない待機は、通常`WAITING`状態になる
- `Object.notify()`は同じモニターで待機するスレッドの一つに通知する
- `notify()`はモニターロックを即座に解放するわけではない
- 通知されたスレッドは、ロックを再取得してから処理を再開する
- `wait()`と`notify()`は、対象オブジェクトのモニターロックを保持した状態で呼び出す
- `wait()`から復帰した後は、待機条件を再確認する必要がある
- 待機条件の確認には、基本的に`while`を使用する
- `notify()`は通知対象となるスレッドを指定できない
- 複数の生産者と消費者が存在する場合、適切でないスレッドが通知される可能性がある
- `notify()`だけでは、すべての待機問題を解決できない
- `synchronized`による排他制御と、`wait()`・`notify()`による協調動作を組み合わせることが重要
- 生産者・消費者問題を通して、マルチスレッド環境における共有データの管理とスレッド間の待機・通知について理解を深めた


## 9. 生産者・消費者問題2

### LockとCondition

- `Condition`は、スレッドの待機と通知を制御するためのインターフェース
- `java.util.concurrent.locks`パッケージに属する
- `ReentrantLock`などの`Lock`と組み合わせて使用する
- `newCondition()`を使用して`Condition`を生成する
- 一つの`Lock`から複数の`Condition`を生成できる
- `Condition`を使用することで、待機するスレッドを種類ごとに分離できる

### Conditionの主なメソッド

| メソッド | 説明 |
| --- | --- |
| `await()` | ロックを解放して待機する |
| `signal()` | 待機中のスレッドを一つ通知する |
| `signalAll()` | 待機中のすべてのスレッドに通知する |
| `awaitNanos()` | 時間制限付きで待機する |

**重要なポイント**

- `await()`を呼び出すには、対応するロックを保持している必要がある
- `await()`はロックを解放してスレッドを待機させる
- 通知を受けたスレッドは、ロックを再取得してから処理を再開する
- `signal()`を実行しても、通知されたスレッドがすぐに処理を再開するとは限らない
- `await()`は予期しない理由で復帰することもあるため、`while`で条件を再確認する

### 例題4 - Conditionによる待機と通知

```java
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class ConditionExample {

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition condition = lock.newCondition();
    private boolean ready = false;

    public void awaitReady() throws InterruptedException {
        lock.lock();
        try {
            while (!ready) {
                condition.await();
            }
            System.out.println("処理再開");
        } finally {
            lock.unlock();
        }
    }

    public void signalReady() {
        lock.lock();
        try {
            ready = true;
            condition.signalAll();
        } finally {
            lock.unlock();
        }
    }
}
```

**処理の流れ**

1. スレッドがロックを取得する
2. `ready`が`false`の場合、`await()`で待機する
3. `await()`によってロックが解放される
4. 別のスレッドがロックを取得する
5. `ready`を`true`に変更する
6. `signalAll()`で待機中のスレッドに通知する
7. 通知されたスレッドがロックを再取得する
8. `while`で条件を再確認し、処理を再開する

### Object.wait()とCondition.await()の違い

| 比較項目 | Object.wait() | Condition.await() |
| --- | --- | --- |
| 使用するロック | モニターロック | Lock |
| 待機 | `wait()` | `await()` |
| 一つの通知 | `notify()` | `signal()` |
| 全体への通知 | `notifyAll()` | `signalAll()` |
| ロック解放 | 対象モニターを解放 | 対応するLockを解放 |
| 待機空間 | オブジェクトごとに一つ | 複数生成可能 |

### 生産者・消費者の待機空間分離

従来の`wait()`と`notify()`では、生産者と消費者が同じ待機空間を使用する。

そのため、通知するスレッドを適切に選択できない場合がある。

`Condition`を使用すると、生産者と消費者の待機空間を分離できる。

**基本構造**

```java
private final ReentrantLock lock = new ReentrantLock();

private final Condition producerCondition = lock.newCondition();
private final Condition consumerCondition = lock.newCondition();
```

- `producerCondition`：キューが満杯の場合に生産者が待機する
- `consumerCondition`：キューが空の場合に消費者が待機する
- 生産者がデータを追加した場合、消費者に通知する
- 消費者がデータを取り出した場合、生産者に通知する
- 同じロックで共有キューへのアクセスを保護する

### 例題5 - Conditionによる待機空間の分離

**学習用コード例**

```java
import java.util.ArrayDeque;
import java.util.Queue;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;

public class BoundedQueueExample {

    private final Queue<String> queue = new ArrayDeque<>();
    private final int max;

    private final ReentrantLock lock = new ReentrantLock();
    private final Condition producerCondition = lock.newCondition();
    private final Condition consumerCondition = lock.newCondition();

    public BoundedQueueExample(int max) {
        this.max = max;
    }

    public void put(String data) throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.size() == max) {
                producerCondition.await();
            }

            queue.offer(data);
            consumerCondition.signal();
        } finally {
            lock.unlock();
        }
    }

    public String take() throws InterruptedException {
        lock.lockInterruptibly();
        try {
            while (queue.isEmpty()) {
                consumerCondition.await();
            }

            String data = queue.poll();
            producerCondition.signal();
            return data;
        } finally {
            lock.unlock();
        }
    }
}
```

**重要なポイント**

- 一つのロックで共有キューを保護する
- 二つの`Condition`で待機空間を分離する
- `await()`中はロックが解放される
- 通知を受けても、条件が成立しているか再確認する
- `try-finally`でロックを確実に解放する
- `lockInterruptibly()`はロック取得待ちの割り込みに対応できる

### スレッドの待機方法の比較

| メソッド | ロックの解放 | 主な状態 |
| --- | --- | --- |
| `Thread.sleep()` | しない | TIMED_WAITING |
| `Object.wait()` | 対象モニターを解放 | WAITING |
| `Condition.await()` | 対応するLockを解放 | WAITING |
| `LockSupport.park()` | 自動解放しない | WAITING |

### BlockingQueueの基本概念

`BlockingQueue<E>`は、複数のスレッドから安全に利用できるキューのインターフェース。

- `java.util.concurrent`パッケージに属する
- スレッド間でデータを安全に受け渡すために使用する
- キューが満杯の場合、生産者を待機させられる
- キューが空の場合、消費者を待機させられる
- ロックや待機・通知の仕組みが内部に実装されている
- `ArrayBlockingQueue`は容量固定の代表的な実装クラス

### 例題6 - BlockingQueue

**学習用コード例**

```java
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.BlockingQueue;

public class BlockingQueueExample {

    public static void main(String[] args) throws InterruptedException {

        BlockingQueue<String> queue = new ArrayBlockingQueue<>(2);

        Thread producer = new Thread(() -> {
            try {
                queue.put("A");
                queue.put("B");
                queue.put("C");
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        Thread consumer = new Thread(() -> {
            try {
                System.out.println(queue.take());
                System.out.println(queue.take());
                System.out.println(queue.take());
            } catch (InterruptedException e) {
                Thread.currentThread().interrupt();
            }
        });

        producer.start();
        consumer.start();

        producer.join();
        consumer.join();
    }
}
```

**処理の流れ**

1. 容量2の`ArrayBlockingQueue`を生成する
2. 生産者がデータをキューに追加する
3. キューが満杯の場合、`put()`で待機する
4. 消費者が`take()`でデータを取り出す
5. 空きができると、生産者は追加処理を進められる
6. キューが空の場合、消費者は要素が追加されるまで待機する

### BlockingQueueの主なメソッド

| 操作 | メソッド | 特徴 |
| --- | --- | --- |
| 追加 | `add(e)` | 追加できなければ例外 |
| 追加 | `offer(e)` | 追加できなければfalse |
| 追加 | `put(e)` | 空きができるまで待機 |
| 取得・削除 | `remove()` | 空なら例外 |
| 取得・削除 | `poll()` | 空ならnull |
| 取得・削除 | `take()` | 要素ができるまで待機 |
| 参照 | `peek()` | 先頭要素を参照し、空ならnull |

**時間制限付きメソッド**

- `offer(e, timeout, unit)`：指定時間内に追加を試みる
- `poll(timeout, unit)`：指定時間内に要素の取得を試みる

### BlockingQueueを使用するメリット

- 自分でロック制御を実装する必要がない
- 待機・通知の仕組みが用意されている
- スレッド間のデータ受け渡しを安全に行える
- 競合状態や通知ミスなどの問題を防ぎやすい
- 生産者・消費者の業務処理に集中できる

### 生産者・消費者問題2のまとめ

- `Condition`は`Lock`と組み合わせて使用する
- `await()`はロックを解放して待機する
- `signal()`と`signalAll()`で待機中のスレッドに通知する
- `Condition`を複数作成することで待機空間を分離できる
- 生産者と消費者に適切な通知を送れる
- `await()`から復帰した場合も`while`で条件を再確認する
- `BlockingQueue`はスレッドセーフなキューを提供する
- `put()`と`take()`によって必要な待機処理を実現できる
- 実際の開発では、標準の並行処理ライブラリを活用することが重要

## 10. CAS - 同期処理とアトミック操作

### 学習した講義

| 講義 | 内容 |
| --- | --- |
| 77 | 原子的操作 - 紹介 |
| 78 | 原子的操作 - 開始 |
| 79 | 原子的操作 - volatile、synchronized |
| 80 | 原子的操作 - AtomicInteger |
| 81 | 原子的操作 - 性能テスト |
| 82 | CAS演算1 |
| 83 | CAS演算2 |
| 84 | CAS演算3 |
| 85 | CASロック実装1 |
| 86 | CASロック実装2 |
| 87 | まとめ |

※ 以下のコードは、講義一覧のテーマに沿って整理した学習用コード例。

### 原子的操作（Atomic Operation）の基本概念

- 原子的操作は、他のスレッドから見て途中の状態が観測されない、一つの不可分な操作
- Javaのソースコードで1行に書かれていても、原子的操作とは限らない
- `count++`は値の読み取り、加算、結果の書き込みで構成される
- 複数のスレッドが同じ値を読み取ると、一方の更新が失われる場合がある
- 可視性は変更が他のスレッドに伝わる性質であり、原子性とは区別する必要がある

**更新が失われる例**

| 順序 | スレッドA | スレッドB | 共有値 |
| --- | --- | --- | --- |
| 1 | 0を読み取る | | 0 |
| 2 | | 0を読み取る | 0 |
| 3 | 1を書き込む | | 1 |
| 4 | | 1を書き込む | 1 |

- 2回加算しても、結果が2ではなく1になる可能性がある
- 実行のたびに問題が再現するとは限らず、正しい結果が出たことだけでは安全性を判断できない

### volatileとsynchronizedの比較

```java
class VolatileCounter {
    private volatile int value;

    public void increment() {
        value++; // 可視性は確保されるが、複合操作は原子的ではない
    }
}

class SynchronizedCounter {
    private int value;

    public synchronized void increment() {
        value++;
    }

    public synchronized int get() {
        return value;
    }
}
```

- `volatile`は読み書きの可視性と順序性を保証するが、排他制御は行わない
- `synchronized`は同じモニターで保護した処理を排他的に実行する
- 同じモニターの解放と、その後の取得によって、共有データの可視性も確保できる
- 共有データを保護するすべてのアクセスで、同期方法を統一する必要がある

### AtomicIntegerの基本概念

- `AtomicInteger`は、整数値を原子的に更新するためのクラス
- `java.util.concurrent.atomic`パッケージに属する
- 単一のカウンターなど、値の更新を安全に実行したい場合に利用できる
- 個々のメソッドが原子的でも、複数の呼び出し全体が自動的に原子的になるわけではない

| メソッド | 説明 |
| --- | --- |
| `get()` | 現在値を取得 |
| `set(value)` | 値を設定 |
| `incrementAndGet()` | 1増加させ、更新後の値を返す |
| `getAndIncrement()` | 1増加させ、更新前の値を返す |
| `decrementAndGet()` | 1減少させ、更新後の値を返す |
| `addAndGet(delta)` | 指定値を加算し、更新後の値を返す |
| `compareAndSet(expected, update)` | 現在値が期待値と一致した場合に更新 |

**コード例**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class AtomicCounterMain {
    public static void main(String[] args) throws InterruptedException {
        AtomicInteger counter = new AtomicInteger();
        Runnable task = () -> {
            for (int i = 0; i < 100_000; i++) {
                counter.incrementAndGet();
            }
        };

        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();

        System.out.println(counter.get());
    }
}
```

**出力例**

```text
200000
```

- 同じ`AtomicInteger`を共有し、各加算を原子的に実行する
- `join()`によって、両方のスレッドが終了してから結果を確認する
- `counter.set(counter.get() + 1)`では、取得から設定までの間に別の更新が入り得るため、代用できない

### 原子的操作の性能テスト

| 方法 | 加算の原子性 | 比較時のポイント |
| --- | --- | --- |
| 通常の`int`で`value++` | 保証しない | 高速でも更新が失われる可能性がある |
| `volatile int`で`value++` | 保証しない | 可視性を確保しても競合は解消しない |
| `synchronized`で保護した加算 | 保証する | ロック競合や待機などの影響を受ける |
| `AtomicInteger`の加算 | 保証する | 競合やキャッシュの共有による影響を受ける |

**測定の流れ**

1. 各方式で同じスレッド数と加算回数を設定する
2. カウンターを初期化し、可能な限り開始条件をそろえる
3. `System.nanoTime()`の差で経過時間を測定する
4. 全スレッドの終了を待ち、期待値と実際の値を比較する
5. ウォームアップ後に複数回測定し、結果のばらつきを確認する

- 単一スレッドと複数スレッドでは、競合の有無が異なる
- スレッドの生成・開始時間を含むかどうかも、測定条件として明確にする
- 測定対象のループ内でログを出すと、出力処理が結果を大きく変える
- JITコンパイルやGC、CPUコア数、OSのスケジューリングによって結果は変化する
- 正確性を満たさない方式を、実用上優れた方式として評価しない
- `AtomicInteger`が常に`synchronized`より高速になるとは限らない
- 厳密なマイクロベンチマークでは、JMHなどの専用ツールを利用する

### CAS演算1 - 比較と更新

CAS（Compare-And-Swap）は、現在値と期待値を比較し、一致した場合だけ新しい値に更新する仕組み。

- 比較と更新を一つの原子的操作として実行する
- CPUの原子的命令などを利用し、JVMがこの操作を実現する
- Javaでは`compareAndSet()`を利用して、条件付きの原子的更新を行える
- 通常の`if`による比較と代入を並べても、同じ保証は得られない

```java
AtomicInteger value = new AtomicInteger(10);

boolean success1 = value.compareAndSet(10, 20);
boolean success2 = value.compareAndSet(10, 30);

System.out.println(success1); // true
System.out.println(success2); // false
System.out.println(value.get()); // 20
```

- 1回目は現在値10と期待値10が一致するため、20に更新される
- 2回目は現在値20と期待値10が一致しないため、更新されない
- `true`はその更新が成功したことを示すが、直後も値が変わらないことまでは保証しない

### CAS演算2 - 競合と再試行

| 順序 | スレッドA | スレッドB | 共有値 |
| --- | --- | --- | --- |
| 1 | 0を取得 | 0を取得 | 0 |
| 2 | CAS(0, 1)に成功 | | 1 |
| 3 | | CAS(0, 1)に失敗 | 1 |
| 4 | | 1を取得し直す | 1 |
| 5 | | CAS(1, 2)に成功 | 2 |

- 他のスレッドが先に更新すると、古い期待値によるCASは失敗する
- 失敗した場合は最新値を読み直し、新しい更新値を計算して再試行する
- 読み取りから計算までを排他制御するのではなく、更新時に期待値との一致を検証する

### CAS演算3 - CASループによる加算

**学習用コード例**

```java
import java.util.concurrent.atomic.AtomicInteger;

public class CasCounter {
    private final AtomicInteger value = new AtomicInteger();

    public int incrementAndGet() {
        int current;
        int next;
        do {
            current = value.get();
            next = current + 1;
        } while (!value.compareAndSet(current, next));
        return next;
    }

    public int get() {
        return value.get();
    }
}
```

- CASに成功するまで、読み取り・計算・条件付き更新を繰り返す
- 成功したCASが、この加算処理の確定点になる
- ロックを保持するスレッドの解放を待たずに、値の更新を試みられる
- 競合が多い場合、特定のスレッドが何度も失敗し、完了まで時間がかかる場合がある
- 標準APIの`incrementAndGet()`の内部が、このコードと常に同じとは限らない
- 複数の変数にまたがる整合性は、一つの`AtomicInteger`だけでは保証できない

### ビジーウェイト（Busy Waiting）

- 条件が成立するまで繰り返し確認しながら待つ方法
- CASの再試行や、スピンロックの取得待ちで見られる
- スレッドを休止させず、実行可能な状態でCPU時間を消費する
- 短い待機では、休止・再開のコストを避けられる場合がある
- 待機が長い場合や競合が多い場合は、CPUを浪費する可能性がある
- `Thread.onSpinWait()`はスピン中であることを実行環境に伝えるヒントであり、待機状態への移行や公平性を保証しない
- CASによる更新の再試行と、他者が保持するロックの解放待ちは、区別して考える必要がある

### CASロック実装1 - フラグによる排他制御の問題

**問題のあるコード例**

```java
private volatile boolean locked;

public void lock() {
    while (locked) {
        Thread.onSpinWait();
    }
    locked = true;
}
```

- `volatile`で変更を観測できても、確認と更新は別々の操作
- 複数のスレッドが同時に`false`を確認すると、両方が`true`を書き込んで臨界領域に入る可能性がある
- ロック取得では、「未取得かの確認」と「取得済みへの変更」を原子的に実行する必要がある

### CASロック実装2 - AtomicBooleanによるスピンロック

**学習用コード例（Java 9以降）**

```java
import java.util.concurrent.atomic.AtomicBoolean;

public class CasSpinLock {
    private final AtomicBoolean locked = new AtomicBoolean();

    public void lock() {
        while (!locked.compareAndSet(false, true)) {
            Thread.onSpinWait();
        }
    }

    public void unlock() {
        locked.set(false);
    }
}
```

**使用例**

```java
// すべてのアクセスで同じlockを共有する
lock.lock();
try {
    // 共有データを操作する臨界領域
} finally {
    lock.unlock();
}
```

- `false`から`true`へのCASに成功したスレッドだけがロックを取得する
- 失敗したスレッドは、解放されるまで取得を繰り返し試みる
- `set(false)`による解放と、その後の取得で使用するアトミック操作は、保護するデータの可視性にも関係する
- 同じスレッドが再び`lock()`すると待ち続けるため、再入可能なロックではない
- 所有者を管理しないため、他のスレッドによる不正な`unlock()`を検出できない
- 公平性、取得タイムアウト、割り込みによる待機中断は実装していない
- CASを使用していても、このロックは保持者の解放に依存するため、ロックフリーなアルゴリズムではない
- 実際の開発では、必要な機能を備えた`synchronized`や`ReentrantLock`などを優先して検討する

### CASとロックの性能比較

| 方法 | 競合時の動作 | 適した場面・注意点 |
| --- | --- | --- |
| CASによる値の更新 | 更新失敗時に再試行できる | 単一の値に対する短い更新に適する |
| 自作スピンロック | 取得できるまでCPUを使って試行 | 長い臨界領域や高競合ではCPU消費が増える |
| `synchronized` | JVMがロック取得と待機を管理 | 複数の操作をまとめて保護できる |
| `ReentrantLock` | 取得できなければ必要に応じて待機 | 割り込み、タイムアウト、Conditionなどを利用できる |

- 標準ロックの内部でも、実装や状況によってCASや短いスピンが使われる場合がある
- 「CASは必ず速い」「ロックは必ずコンテキストスイッチを起こす」とは判断しない
- 競合の程度、臨界領域の長さ、スレッド数とCPUコア数を考慮する
- 性能だけでなく、正確性、CPU使用率、応答時間、保守性も比較する
- 今回の資料には実測結果がないため、特定の処理時間や性能順位は記録しない

### CAS - 同期処理とアトミック操作のまとめ

- `count++`は複合操作であり、`volatile`だけでは原子性を保証できない
- `AtomicInteger`を利用すると、整数の加算などを原子的に実行できる
- `compareAndSet()`は、期待値の比較と更新を原子的に実行する
- CASに失敗した場合は、必要に応じて最新値を取得して再試行する
- ビジーウェイトはCPUを使用するため、競合や待機時間によって効率が変わる
- CASでスピンロックを実装できるが、実用的なロックには追加の機能や検証が必要になる
- 単一の値の更新と、複数の操作をまとめて保護する処理では、適切な同期方法が異なる
- 性能比較では結果の正確性を確認し、測定条件と実行環境をそろえる

**参考資料**

- [Java公式API - AtomicInteger](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/atomic/AtomicInteger.html)

## 11. 並行処理コレクション

### 学習した講義

| 講義 | 内容 |
| --- | --- |
| 88 | 並行処理コレクションが必要な理由1 - 開始 |
| 89 | 並行処理コレクションが必要な理由2 - 同時実行問題 |
| 90 | 並行処理コレクションが必要な理由3 - 同期処理 |
| 91 | 並行処理コレクションが必要な理由4 - プロキシの導入 |
| 92 | Javaの並行処理コレクション1 - synchronized |
| 93 | Javaの並行処理コレクション2 - 並行処理コレクション |

※ 講座のセクション12に対応。以下のコードは、講義一覧のテーマに沿って整理した学習用コード例であり、講義のソースコードをそのまま再現したものではない。

### 並行処理コレクションが必要な理由1 - 開始

- `ArrayList`、`HashSet`、`HashMap`などは、通常、内部の変更を自動的に同期しない
- 複数のスレッドで共有し、少なくとも一つが変更する場合は、適切な同期が必要になる
- 要素の格納、要素数の更新、容量の拡張などを含む整合性を維持する必要がある

**学習用コード例 - 同期していないリスト**

```java
interface SimpleList {
    void add(Object value);
    int size();
}

class BasicList implements SimpleList {
    private final Object[] elements = new Object[1000];
    private int size;

    public void add(Object value) {
        elements[size] = value;
        size++;
    }

    public int size() {
        return size;
    }
}
```

- 配列への格納と`size`の更新は、一つの原子的操作ではない
- 説明のため容量拡張と境界チェックを省略している
- 実際の`ArrayList`の内部実装をそのまま表すコードではない

### 並行処理コレクションが必要な理由2 - 同時実行問題

| 順序 | スレッドA | スレッドB |
| --- | --- | --- |
| 1 | 格納先としてsize = 0を読む | |
| 2 | | 格納先としてsize = 0を読む |
| 3 | elements[0]にAを格納 | |
| 4 | | elements[0]にBを格納 |
| 5 | sizeを更新 | sizeを更新 |

- 同じ位置に書き込むと、一方の要素が上書きされる
- `size++`でも競合が発生し、要素数の更新が失われる可能性がある
- 要素数が正しくても、格納した内容まで正しいとは限らない
- `size`だけを`volatile`や`AtomicInteger`に変更しても、リスト全体の整合性は自動的には保証されない
- 問題が毎回再現するとは限らず、一度正しく動いたことだけでは安全性を判断できない
- `sleep()`は実行タイミングを変えるが、排他制御や可視性を保証しない

### 並行処理コレクションが必要な理由3 - 同期処理

```java
class SynchronizedBasicList implements SimpleList {
    private final Object[] elements = new Object[1000];
    private int size;

    public synchronized void add(Object value) {
        elements[size] = value;
        size++;
    }

    public synchronized int size() {
        return size;
    }
}
```

- 同じインスタンスのモニターで、格納先の確認から要素数の更新までを保護する
- 読み取りも同じロックを利用し、変更の可視性を確保する
- 同じロックが必要な操作は、一度に一つのスレッドだけが実行する
- 取得や削除を追加する場合も、同じ同期方針に従う必要がある
- 各実装に同期処理を書くと、コードの重複や同期の追加漏れが発生しやすい

### 並行処理コレクションが必要な理由4 - プロキシの導入

プロキシは、同じインターフェースを実装し、実際の処理を別のオブジェクトに委譲する仕組み。

```java
class SynchronizedListProxy implements SimpleList {
    private final SimpleList target;

    public SynchronizedListProxy(SimpleList target) {
        this.target = target;
    }

    public synchronized void add(Object value) {
        target.add(value);
    }

    public synchronized int size() {
        return target.size();
    }
}

// 複数のスレッドには、この同じlistを渡す
// SimpleList list = new SynchronizedListProxy(new BasicList());
```

- 元のクラスを変更せず、同期処理をプロキシにまとめられる
- 呼び出し側は同じ`SimpleList`インターフェースを利用できる
- ロックの対象はプロキシ自身であるため、同じプロキシを共有する必要がある
- 同じ元データを別々のプロキシで包むと、ロックが分かれて競合を防げない
- 元のオブジェクトを直接操作すると、同期処理を迂回してしまう

### Javaの並行処理コレクション1 - synchronized

`Collections`の同期ラッパーは、既存のコレクションを同期機能付きで扱うための仕組み。

```java
// 以下の型はjava.utilパッケージに属する
List<String> list = Collections.synchronizedList(new ArrayList<>());
Set<String> set = Collections.synchronizedSet(new HashSet<>());
Map<String, Integer> map = Collections.synchronizedMap(new HashMap<>());
```

**実行可能なコード例**

```java
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class SynchronizedListMain {
    public static void main(String[] args) throws InterruptedException {
        List<Integer> list = Collections.synchronizedList(new ArrayList<>());
        Runnable task = () -> {
            for (int i = 0; i < 1000; i++) {
                list.add(i);
            }
        };
        Thread thread1 = new Thread(task);
        Thread thread2 = new Thread(task);
        thread1.start();
        thread2.start();
        thread1.join();
        thread2.join();
        System.out.println(list.size()); // 2000
    }
}
```

- 同じラッパーを共有し、各`add()`を同期して実行する
- `join()`で全スレッドの終了を待ってから結果を確認する
- 追加順序は保証されない

### 複合操作と反復処理の注意点

```java
// contains()とadd()の間に、別の追加が入る可能性がある
if (!list.contains("A")) {
    list.add("A");
}

// 同期ラッパー自身をロックし、確認と追加をまとめて保護する
synchronized (list) {
    if (!list.contains("A")) {
        list.add("A");
    }
}

// 反復処理も全体を保護する
synchronized (list) {
    for (String value : list) {
        System.out.println(value);
    }
}
```

- 個々のメソッドが安全でも、一連の処理が自動的に原子的になるわけではない
- 同期ラッパーの`Iterator`、`Spliterator`、`Stream`による走査では、外部同期が必要になる
- 同期マップの`keySet()`などを走査する場合は、元の同期マップをロックする
- 関係するアクセスが同じロックに従うことが前提になる
- `ConcurrentModificationException`は変更を必ず検出する保証ではなく、同期の代わりにはならない

### Javaの並行処理コレクション2 - 並行処理コレクション

| 分類 | 代表的な実装 | 主な用途・特徴 |
| --- | --- | --- |
| List | `CopyOnWriteArrayList` | 読み取り・走査が多く、更新が少ない用途 |
| Set | `CopyOnWriteArraySet` | 更新が少ない集合 |
| Map | `ConcurrentHashMap` | キーと値の並行した参照・更新 |
| ソート済みMap | `ConcurrentSkipListMap` | キー順を維持するマップ |
| ソート済みSet | `ConcurrentSkipListSet` | 順序を維持する集合 |
| Queue | `ConcurrentLinkedQueue` | 非ブロッキングのFIFOキュー |
| Deque | `ConcurrentLinkedDeque` | 両端から操作するキュー |
| BlockingQueue | `ArrayBlockingQueue`、`LinkedBlockingQueue` | 空・満杯などの条件に応じて待機する受け渡し |

- 各クラスは、ロック、CAS、コピーなど、それぞれの特性に応じた方法を利用する
- すべてがロックフリーであるわけではない
- 同期ラッパーより必ず高速になるとは限らず、読み取り・更新の頻度や競合状況で選択する

### CopyOnWriteArrayListの使用例

```java
// import java.util.Iterator;
// import java.util.concurrent.CopyOnWriteArrayList;
CopyOnWriteArrayList<String> list = new CopyOnWriteArrayList<>();
list.add("A");
list.add("B");
Iterator<String> iterator = list.iterator();
list.add("C");
while (iterator.hasNext()) {
    System.out.println(iterator.next()); // A、B
}
list.addIfAbsent("C"); // 存在するため追加しない
```

- イテレーターは生成時点のスナップショットを参照し、その後の追加を反映しない
- 更新時のコピーに時間とメモリを使うため、更新が頻繁な用途には適さない場合がある
- イテレーターの`remove()`などによる変更はサポートされない
- スナップショットは、要素オブジェクト自体を深くコピーするものではない

### ConcurrentHashMapの使用例

```java
// import java.util.concurrent.ConcurrentHashMap;
ConcurrentHashMap<String, Integer> counts = new ConcurrentHashMap<>();
counts.putIfAbsent("java", 0);
counts.merge("java", 1, Integer::sum);
System.out.println(counts.get("java")); // 1
```

- `putIfAbsent()`は、キーが存在しない場合の追加を原子的に実行する
- `merge()`は対象キーの値の結合・更新を原子的に実行する
- `get()`してから`put()`する加算では、その間に別の更新が入る可能性がある
- 対象キーの原子的更新と、マップ全体の一括したスナップショットは別の性質
- キーにも値にも`null`を使用できない
- `synchronized (map)`だけでは通常の並行更新を止められず、マップ全体の排他制御にはならない

### 反復処理と待機動作の比較

| 対象 | 動作 | 注意点 |
| --- | --- | --- |
| 同期ラッパー | 走査全体を外部同期で保護 | 個々のメソッドの同期だけでは不足 |
| `CopyOnWriteArrayList` | 生成時点のスナップショットを走査 | 後からの変更を反映しない |
| `ConcurrentHashMap`など | 弱整合性のイテレーターを提供 | 走査中の変更を反映する場合があり、全体の一時点の状態とは限らない |
| `ConcurrentLinkedQueue` | 空の場合、`poll()`はnullを返す | 要素が来るまで待機する操作ではない |
| `BlockingQueue` | 空の場合、`take()`は待機する | 割り込みや終了方法を設計する |

### 並行処理コレクションのまとめ

- 通常のコレクションを複数のスレッドで変更する場合は、適切な同期が必要
- 整合性を維持する一連の処理を、同じロックで保護する
- 同期プロキシは、実際の処理と同期処理を分離できる
- 同期ラッパーを使用する場合は、同じラッパーを共有する
- 個々の操作と、複合操作・走査全体の安全性を区別する
- 並行処理コレクションでは、用途に適した原子的操作を利用する
- 順序、読み取り・更新の頻度、反復の性質、待機の必要性を考慮して選択する
- 格納した可変オブジェクトの変更まで自動的に同期されるわけではない

**参考資料**

- [Java公式API - Collections](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/Collections.html)
- [Java公式API - java.util.concurrent](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/package-summary.html)
- [Java公式API - CopyOnWriteArrayList](https://docs.oracle.com/en/java/javase/25/docs/api/java.base/java/util/concurrent/CopyOnWriteArrayList.html)
