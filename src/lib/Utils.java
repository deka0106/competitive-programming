package lib;

import java.lang.reflect.Array;
import java.util.*;
import java.util.function.Function;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class Utils {

	public static void main(String[] args) {

	}

	// 0 ~ 2^N
	public static void allCheck() {
		int N = 4;
		for (int i = 0; i < 1 << N; i++) {
			for (int j = 0; j < N; j++) {
				System.out.print((i >> j) & 1);
			}
			System.out.println();
		}
	}

	/**
	 * 二分探索
	 */
	public static int binarySearch(int ok, int ng, Function<Integer, Boolean> callback) {
		while (Math.abs(ok - ng) > 1) {
			int mid = (ok + ng) / 2;
			if (callback.apply(mid)) ok = mid;
			else ng = mid;
		}
		return ok;
	}

	/**
	 * 最大公約数
	 */
	public static int gcd(int n, int m) {
		return m == 0 ? n : gcd(m, n % m);
	}

	/**
	 * 最大公約数
	 */
	public static long gcd(long n, long m) {
		return m == 0 ? n : gcd(m, n % m);
	}

	/**
	 * 最小公倍数
	 */
	public static int lcm(int n, int m) {
		return n * (m / gcd(n, m));
	}

	/**
	 * 最小公倍数
	 */
	public static long lcm(long n, long m) {
		return n * (m / gcd(n, m));
	}

	/**
	 * 拡張ユークリッド互除法
	 * nx + my = gcd(n, m) となる n, m の最大公約数と解 x, y
	 */
	public static int extgcd(int n, int m, int[] x, int[] y) {
		int g = n;
		if (m != 0) {
			g = extgcd(m, n % m, y, x);
			y[0] -= (n / m) * x[0];
		} else {
			x[0] = 1;
			y[0] = 0;
		}
		return g;
	}

	/**
	 * 拡張ユークリッド互除法
	 * nx + my = gcd(n, m) となる n, m の最大公約数と解 x, y
	 */
	public static long extgcd(long n, long m, long[] x, long[] y) {
		long g = n;
		if (m != 0) {
			g = extgcd(m, n % m, y, x);
			y[0] -= (n / m) * x[0];
		} else {
			x[0] = 1;
			y[0] = 0;
		}
		return g;
	}

	/**
	 * n^m % mod
	 */
	public static int modPow(long n, long m, long mod) {
		if (m == 0) return 1;
		if (m % 2 == 0) return modPow(n * n % mod, m / 2, mod);
		return (int) (modPow(n, m - 1, mod) * n % mod);
	}

	/**
	 * n^(-1) % mod
	 */
	public static int modInv(long n, long mod) {
		long[] x = new long[1], y = new long[1];
		extgcd(n, mod, x, y);
		return (int) ((x[0] % mod + mod) % mod);
	}

	/**
	 * 素数判定
	 */
	public static boolean isPrime(long n) {
		if (n < 2) return false;
		else if (n == 2) return true;
		else if (n % 2 == 0) return false;
		double sqrt = Math.sqrt(n);
		for (int i = 3; i <= sqrt; i += 2) {
			if (n % i == 0) return false;
		}
		return true;
	}

	/**
	 * n以下の素数
	 */
	public static TreeSet<Integer> primes(int n) {
		TreeSet<Integer> numbers = new TreeSet<>();
		TreeSet<Integer> primes = new TreeSet<>();
		for (int i = 3; i <= n; i += 2) {
			numbers.add(i);
		}
		int prime = 2;
		primes.add(2);
		while (numbers.last() > prime * prime) {
			prime = numbers.first();
			primes.add(prime);
			Iterator<Integer> itr = numbers.iterator();
			while (itr.hasNext()) {
				if (itr.next() % prime == 0) itr.remove();
			}
		}
		primes.addAll(numbers);
		return primes;
	}

	/**
	 * nextPermutation
	 * 0 1 2 3 -> 0 1 3 2 -> 0 2 1 3 -> 0 2 3 1 -> ...
	 */
	public static <T extends Comparable<? super T>> boolean nextPermutation(List<T> list) {
		int size = list.size();
		for (int i = size - 1; i > 0; --i) {
			if (list.get(i - 1).compareTo(list.get(i)) < 0) {
				T tmp = list.get(i - 1);
				Collections.swap(list, i - 1, binarySearch(i, size, j -> list.get(j).compareTo(tmp) > 0));
				Collections.sort(list.subList(i, size));
				return true;
			}
		}
		return false;
	}

	/**
	 * nextPermutation
	 * 0 1 2 3 -> 0 1 3 2 -> 0 2 1 3 -> 0 2 3 1 -> ...
	 */
	public static <T extends Comparable<? super T>> boolean nextPermutation(T[] array) {
		int size = array.length;
		for (int i = size - 1; i > 0; --i) {
			if (array[i - 1].compareTo(array[i]) < 0) {
				T tmp = array[i - 1];
				swap(array, i - 1, binarySearch(i, size, j -> array[j].compareTo(tmp) > 0));
				Arrays.sort(array, i, size);
				return true;
			}
		}
		return false;
	}

	/**
	 * nextPermutation
	 * 0 1 2 3 -> 0 1 3 2 -> 0 2 1 3 -> 0 2 3 1 -> ...
	 */
	public static boolean nextPermutation(int[] array) {
		int size = array.length;
		for (int i = size - 1; i > 0; --i) {
			if (array[i - 1] < array[i]) {
				int tmp = array[i - 1];
				swap(array, i - 1, binarySearch(i, size, j -> array[j] > tmp));
				Arrays.sort(array, i, size);
				return true;
			}
		}
		return false;
	}

	/**
	 * ディープコピー
	 */
	@SuppressWarnings("unchecked")
	public static <T> T[] deepCopyOf(T[] array) {
		if (array.length <= 0) return array;
		return (T[]) deepCopyOf(
			array,
			Array.newInstance(array[0].getClass(), array.length),
			0);
	}

	private static Object deepCopyOf(Object array, Object copiedArray, int index) {
		if (index >= Array.getLength(array)) return copiedArray;
		Object element = Array.get(array, index);
		if (element.getClass().isArray()) {
			Array.set(copiedArray, index, deepCopyOf(
				element,
				Array.newInstance(element.getClass().getComponentType(), Array.getLength(element)),
				0));
		} else {
			Array.set(copiedArray, index, element);
		}
		return deepCopyOf(array, copiedArray, ++index);
	}

	/**
	 * 配列埋め
	 */
	public static void deepFill(Object array, Object value) {
		if (array.getClass().getComponentType().isArray()) {
			for (Object o : (Object[]) array) {
				deepFill(o, value);
			}
		} else if (array instanceof boolean[]) {
			Arrays.fill((boolean[]) array, (boolean) value);
		} else if (array instanceof char[]) {
			Arrays.fill((char[]) array, (char) value);
		} else if (array instanceof int[]) {
			Arrays.fill((int[]) array, (int) value);
		} else if (array instanceof long[]) {
			Arrays.fill((long[]) array, (long) value);
		} else if (array instanceof double[]) {
			Arrays.fill((double[]) array, (double) value);
		} else {
			Arrays.fill((Object[]) array, value);
		}
	}

	/**
	 * 入れ替え
	 */
	public static <T> void swap(T[] array, int i, int j) {
		T tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/**
	 * 入れ替え
	 */
	public static void swap(int[] array, int i, int j) {
		int tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/**
	 * 入れ替え
	 */
	public static void swap(long[] array, int i, int j) {
		long tmp = array[i];
		array[i] = array[j];
		array[j] = tmp;
	}

	/**
	 * 文字列結合
	 */
	public static <T> String join(String separator, Iterable<T> iterable) {
		StringBuilder sb = new StringBuilder();
		Iterator<T> iterator = iterable.iterator();
		while (iterator.hasNext()) {
			sb.append(iterator.next());
			if (iterator.hasNext()) sb.append(separator);
		}
		return sb.toString();
	}

	/**
	 * 文字列結合
	 */
	public static String join(String separator, int[] array) {
		return Arrays.stream(array).mapToObj(String::valueOf).collect(Collectors.joining(separator));
	}

	/**
	 * 文字列置換
	 */
	public static String replace(String str, String regex, Function<List<String>, String> callback) {
		StringBuffer sb = new StringBuffer();
		Matcher m = Pattern.compile(regex).matcher(str);
		while (m.find()) {
			List<String> groups = new ArrayList<>();
			for (int i = 0; i <= m.groupCount(); i++) {
				groups.add(m.group(i));
			}
			m.appendReplacement(sb, callback.apply(groups));
		}
		m.appendTail(sb);
		return sb.toString();
	}

	/**
	 * デバッグ出力
	 */
	public void debug(Object... os) {
		System.err.println(Arrays.deepToString(os));
	}

}
