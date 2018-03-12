Change Log
==========

Version 0.2 *(2018-02-28)*
--------------------------

 * New: `File.toUri` extension for `Uri.fromFile`.
 * New: `TypedArray.getResourceIdOrThrow` extension for ensuring an attribute value is set before calling `getResourceId`.
 * New: `TypedArray.use` extension for automatically recycling a `TypedArray` after the supplied lambda was executed.
 * New: `ViewGroup.children` extension returns a `Sequence<View>` for lazily iterating.
 * New: `View.isVisible`, `View.isInvisible`, and `View.isGone` mutable properties allow toggling a view's visibility between two corresponding values.
 * New: `String.toColorInt` extension parses a hexidecimal color into its integer representation.
 * New: `Locale.layoutDirection` extension exposes the layout direction of the locale instance.
 * New: `lruCache` function allows easy creation of an `LruCache` without subclassing.
 * New: `CharSequence.toSpanned` and `CharSequence.toSpannable` extensions for convenient conversion.
 * New: `Spanned.getSpans` reified extension simplifies extracting spans of a particular type.
 * New: `Spannable.plusAssign` and `Spannable.minusAssign` extensions allow using `+=` or `-=` to add or remove spans, respectively, from the entire spannable.
 * New: `Spannable.clearSpans` convenience extension to remove all spans.
 * New: `SpannableStringBuilder.strikeThrough` and `SpannableStringBuilder.scale` for use inside `buildSpannedString`.
 * New: `SpannableStringBuilder.inSpans` overload for a single span to reduce overhead for the common case.
 * New: `SharedPreferences.edit` now has a `commit` argument which defaults to `false`. Set to `true` for using `commit()` to complete the edit.
 * New: `String.htmlEncode` extension for escaping HTML entities in the string.
 * New: `CharSequence.trimmedLength` extension returns the number of characters if whitespace was trimmed.
 * New: `CharSequence.isDigitsOnly` extension indicates whether only digits comprise the sequence.
 * New: `ViewGroup.updateLayoutParams` extension allows modification of layout parameters and automatically applies any changes.
 * New: Extensions to `Menu` to treat it more like a collection of `MenuItem`s.
 * Fix: Correct `Sparse*` extensions to treat all negative numbers as a missing entry, not just -1.
 * Fix: `doOnLayout` now checks for `isLayoutRequested` and schedules its lambda after layout when true.
 * Fix: Include `@Px` annotations on pixel-based arguments.
 * Some existing functions were marked as `inline` where appropriate.
 * Extensions to `java.time` types are deprecated with no replacement (other than the normal functions that they call). This library is focused on extensions for the `android.*` packages only.


Version 0.1 *(2018-02-05)*
--------------------------

Initial release.
