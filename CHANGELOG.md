Change Log
==========

Version 1.0.0-alpha1 *(2018-05-07)*
-----------------------------------

Note: Support library dependencies have changed to AndroidX!

 * New: `superscript` and `subscript` span blocks for `buildString`/`SpannableStringBuilder`.
 * New: `Menu.children` extension returns a `Sequence<MenuItem>` for lazily iterating.
 * New: `Menu.minusAssign` operator extension allow using `-=` to remove a `MenuItem`.
 * New: `PreferenceGroup.children` extension returns a `Sequence<Preference>` for lazily iterating.
 * New: `Location` destructuring extensions for latitude and longitude.
 * Fix: `View.toBitmap` now takes into account the view's scroll position.


Version 0.3 *(2018-04-02)*
--------------------------

Note: All extensions have moved into the `androidx.core.*` package.

 * New: `Canvas.withMatrix` extension for manipulating a `Canvas` after `concat`enating a matrix.
 * New: Add `start` and `end` parameters for narrowing `Spanned.getSpans` lookup.
 * New: Extensions to `PreferenceGroup` to treat it more like a collection of `Preference`s.
 * New: `View.announceForAccessibility` extensions which takes a resource ID instead of a string.
 * New: `Spannable.set` operator extensions for adding a span with an `IntRange` or starting and ending index.
 * New: `Context.toast` extension for showing simple `Toast`s.
 * New: `Uri.toFile` extension to extract a `File` from the `Uri` path.
 * New: `String.parseAsHtml` extension which calls `Html.fromHtml` on the string.
 * New: `Spanned.toHtml` extension calls `Html.toHtml` on the supplied `Spanned` instance.
 * Fix: Correct a logic bug in `Path.flatten` which would cause it omit some segments.
 * Fix: Move `updateLayoutParams` extension from `ViewGroup` to `View`.
 * Fix: Move `File.toUri` extension into 'net' package.
 * `Handler.post*` overloads accepting a `TimeUnit` and `Duration` have been removed.
 * `SparseBooleanArray.removeAt` has been removed as the function has been added directly on the type.
 * Extensions to `java.time` types have been removed.


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
