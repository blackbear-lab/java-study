# Java 上級1

Javaのマルチスレッドと並行処理について学習します。

## 学習状況

- [x] プロセスとスレッドの概要
- [x] スレッドの生成と実行
- [x] スレッドの制御とライフサイクル1
- [ ] スレッドの制御とライフサイクル2
- [ ] メモリの可視性
- [ ] 同期処理 - synchronized
- [ ] 高度な同期処理 - concurrent.Lock
- [ ] 生産者・消費者問題1
- [ ] 生産者・消費者問題2
- [ ] CAS - 同期処理とアトミック操作
- [ ] 並行処理コレクション
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
