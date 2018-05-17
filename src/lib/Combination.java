package lib;

/**
 * 組み合わせ総数
 */
public class Combination {

	private final int mod;
	private final long[] fact;
	private final long[] inv;
	private final long[] invfact;

	public Combination(int n, int mod) {
		this.mod = mod;
		this.fact = new long[n + 1];
		this.invfact = new long[n + 1];
		this.inv = new long[n + 1];

		inv[1] = 1;
		for (int i = 2; i < inv.length; i++) {
			inv[i] = inv[mod % i] * (mod - mod / i) % mod;
		}

		fact[0] = 1;
		invfact[0] = 1;
		for (int i = 1; i < inv.length; i++) {
			fact[i] = i * fact[i - 1] % mod;
			invfact[i] = inv[i] * invfact[i - 1] % mod;
		}
	}

	/**
	 * nCr % mod
	 */
	public long get(int n, int r) {
		if (n < r) return 0;
		return fact[n] * invfact[n - r] % mod * invfact[r] % mod;
	}

}

